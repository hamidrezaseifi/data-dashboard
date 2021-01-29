<template>
  <div class="newworkbook">

    <div class="card newworkbook">
      <div class="card-header">

        <b>New Workbook</b>
        <button type="button" class="btn" v-on:click="showSelectSourceDialog()"><img src="@/assets/images/database_add.png" width="20" /></button>
        <router-link class="" to="/data/settings/connections"><img src="@/assets/images/card-list.svg" width="20" /></router-link>
      </div>
      <div class="card-body">

        <form id="newconnectionform" @submit.prevent="handleSaveData">

          <ul class="nav nav-tabs">
            <li class="nav-item">
                <a class="nav-link active" id="datasourcestab" data-toggle="tab" href="#datasourcestabcontent" role="tab" aria-controls="home" aria-selected="true">Datenquelle</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" id="columnstab" data-toggle="tab" href="#columnstabcontent" role="tab" aria-controls="profile" aria-selected="false">Spalten</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" id="presentationtab" data-toggle="tab" href="#presentationtabcontent" role="tab" aria-controls="profile" aria-selected="false">Präsentation</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" id="finaltab" data-toggle="tab" href="#profile" role="tab" aria-controls="finaltabcontent" aria-selected="false">Finalisieren</a>
            </li>
          </ul>
          <div class="tab-content" id="myTabContent">
              <div class="tab-pane fade show active tabs-content" id="datasourcestabcontent" role="tabpanel" aria-labelledby="datasourcestab">
                  <WorkbookDataSourceItem  v-for="item in workbookDataSourceList" :key="item.table"
                                           v-on:removeWorkbookItem="removeWorkbookItem" v-bind:workbookDataSourceItem="item">

                  </WorkbookDataSourceItem>

                  <div class="clear"></div>

              </div>
              <div class="tab-pane fade tabs-content" id="columnstabcontent" role="tabpanel" aria-labelledby="columnstab">

              </div>
              <div class="tab-pane fade tabs-content" id="presentationtabcontent" role="tabpanel" aria-labelledby="presentationtab">

              </div>
              <div class="tab-pane fade tabs-content" id="finaltabcontent" role="tabpanel" aria-labelledby="finaltab">

              </div>
          </div>

          <div class="form-group" style="margin-top: 10px;">
            <span v-if="errmessage != ''" class="alert alert-danger" style="float:left; max-width: calc(100% - 300px);" v-html="errmessage"></span>
            <span v-if="okmessage != ''" class="alert alert-success" style="float:left; max-width: calc(100% - 300px);" v-html="okmessage"></span>
            <button type="submit" class="btn btn-primary actionbutton" style="float:right;">{{saveButtonName}}</button>
            <button type="button" class="btn btn-secondary actionbutton" style="float:right;">Test</button>
          </div>
        </form>

      </div>
    </div>

    <DataSourceSelectDialog v-bind:connectionList="getConnectionList" v-on:sourceSelected="addSelectedColumns"
                            v-bind:isDialogVisible="getSelectSourceDialogVisible" v-on:close="closeSelectSourceDialog">

    </DataSourceSelectDialog>

  </div>
</template>

<style>

.actionbutton{
  margin-left: 10px;
  width: 120px;
}

.card.newworkbook{
  width: 80%;
  margin: 10px auto;
}

input.form-control {
    display: inline-block;
    width: calc(100% - 200px);
}


input.form-control , select.form-control {
    display: inline-block;
    width: calc(100% - 170px);
}

.tabs-content{
    min-height: calc(100vh - 310px);
    max-height: calc(100vh - 310px);
    border: 1px solid #dee2e6;
    border-top: none;
    padding: 10px;
}
</style>
<script>
import { dataSettingsService } from '../../../../services/datasettings.service';
import DataSourceSelectDialog from '../../../../components/data/DataSourceSelectDialog.vue';
import WorkbookDataSourceItem from '../../../../components/data/WorkbookDataSourceItem.vue';


import router from '../../../../router'
import $ from 'jquery'

export default {
    name: 'NewConnection',
    data () {
        return {
          connectionList:[],
          errmessage: "",
          okmessage: "",
          id: false,
          isSelectSourceDialogVisible: false,
          currentUpdateDateTime: new Date(),
          workbookDataSourceList: []
        }
    },
    components:{
        DataSourceSelectDialog,
        WorkbookDataSourceItem
    },
    props:["inBox", "clone"],
    computed: {
      saveButtonName: function (){
        return this.isNew() ? "Erstellen" : "Speiren"
      },
      isAddDataSourceDisabled: function (){
        this.currentUpdateDateTime
        return this.selectedColumnList.length == 0
      },
      getSelectSourceDialogVisible: function (){
        this.currentUpdateDateTime
        return this.isSelectSourceDialogVisible
      },
      getConnectionList: function (){
        this.currentUpdateDateTime
        return this.connectionList
      }
    },
    methods: {
      loadConnections(){
        dataSettingsService.getAllConnections().then(async response => {
            const data = await response.json();
            if (!response.ok) {
              const error = (data && data.message) || response.statusText;
              return Promise.reject(error);
            }

            this.connectionList = data;
            this.currentUpdateDateTime = new Date()

            }).catch(error => {
              console.error("There was an error!", error);
            })
      },
      handleSaveData () {

        var func = false;
        if(this.isEdit()){
          func = dataSettingsService.updateConnection(this.connection)
        }
        else{
          func = dataSettingsService.createConnection(this.connection)
        }

        this.submitted = true;
        func.then(async response => {
          const data = await response.json();

          if (!response.ok) {
            var error = (data && data.errmessage) || response.statusText;

            this.errmessage = data.errmessage;
            if(data._embedded && data._embedded.errors){
              for(var i in data._embedded.errors){
                this.errmessage += "<br>" + data._embedded.errors[i].errmessage;
              }

            }
            return Promise.reject(error);
          }

          this.connection = data;
          router.push('/data/settings/connections');

        })
      },
      isNew(){
        return this.id == false;
      },
      isEdit(){
        return this.id;
      },
      isClone(){
        return this.clone;
      },
      reloadData(){
        if(this.isEdit()){
          this.loadConnectionData();
        }
        if(this.isNew()){
          this.loadInitData()
        }
        if(this.isClone()){
          this.loadCloneData()
        }
      },
      addSelectedColumns(item){
        this.workbookDataSourceList.push(item)
        this.isSelectSourceDialogVisible = false
        this.currentUpdateDateTime = new Date()

        $('#datasourcestab').tab('show')

      },
      getColumnLabel(item){
        return item.name + " : " + item.type + "(" + item.size + ")"
      },
      showSelectSourceDialog(){
        this.isSelectSourceDialogVisible = true
        this.currentUpdateDateTime = new Date()

      },
      closeSelectSourceDialog(){
        this.isSelectSourceDialogVisible = false
        this.currentUpdateDateTime = new Date()

      },
      removeWorkbookItem(removeItem){
        this.workbookDataSourceList = this.workbookDataSourceList.filter(item => ((item.table != removeItem.table) | (item.connection.id != removeItem.connection.id)))

      }

    },
    created () {
      this.loadConnections()

    }
};
</script>

