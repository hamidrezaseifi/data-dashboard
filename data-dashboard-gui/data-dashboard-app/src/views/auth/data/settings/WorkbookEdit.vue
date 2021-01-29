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
                  <div class="card workbook-item" v-for="item in workbookDataSourceList" :key="item.table">
                      <div class="card-header">
                          <span><b>{{item.connection.name}}</b></span>
                          <br>
                          <span>{{item.table}}</span>
                          <button type="button" class="close" aria-label="Close" v-on:click="removeWorkbookItem(item)">
                              <span aria-hidden="true">&times;</span>
                          </button>
                      </div>
                      <div class="card-body">
                          <ul  class="list-group">
                              <li class="list-group-item" v-for="column in item.columns" :key="column.name" >

                                  <span style="margin-left: 9px;">{{ getColumnLabel(column)}}</span>
                              </li>
                          </ul >

                      </div>
                  </div>
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
            <button type="button" class="btn btn-secondary actionbutton" v-on:click="testConnection" style="float:right;">Test</button>
          </div>
        </form>

      </div>
    </div>

    <div class="modal fade " id="datasourceDialog" v-bind:class="{ show: isSelectSourceDialogVisible }" v-if="isSelectSourceDialogVisible" style="display: block;"
         tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
      <div class="modal-dialog modal-dialog-centered" role="document" style="width: 820px; max-width: 820px;">
        <div class="modal-content" style="height: calc(100vh - 200px);">
          <div class="modal-header">
            <h5 class="modal-title" id="exampleModalLongTitle">Datenquelle außwählen ..</h5>
            <button type="button" class="close" data-dismiss="modal" aria-label="Close" v-on:click="isSelectSourceDialogVisible=false">
              <span aria-hidden="true">&times;</span>
            </button>
          </div>
          <div class="modal-body">
              <div class="form-group">
                <label for="connectionlist" class="item-title">Verbindung-Liste</label>
                <select class="form-control" id="connectionlist" @change="onConnectionChange($event)" v-model="selectedConnectionId" name="selectedConnectionId">
                  <option v-for="item in connectionList" :key="item.id" v-bind:value="item.id" >{{item.name}}</option>
                </select>
              </div>
              <div class="sub-item-container" >
                <div class="sub-item">

                    <label class="top">Tabelle-Liste</label>
                    <input class="search" id="keyfilterTableText" placeholder="" v-model="filterTableText">
                    <div  class="sub-item-list">
                      <div class="list-group">
                        <button type="button" class="list-group-item list-group-item-action" v-on:click="loadTableColumns(item)" v-for="item in filterTableList" :key="item" >{{item}}</button>
                      </div>
                    </div>


                </div>
                <div class="sub-item">

                    <label class="top">Spalten-Liste</label>
                    <input class="search" id="keyfiltercolumnText" placeholder="" v-model="filterColumnText">
                    <label class="top">Alle</label>
                    <input class="search-checkall" type="checkbox" v-on:click="selectAllColumns($event)">
                    <div class="sub-item-list">
                      <ul  class="list-group">
                        <li class="list-group-item" v-for="item in filterColumnList" :key="item.name" >
                          <input type="checkbox" :checked="item.selected" v-on:click="item.selected = !item.selected; currentUpdateDateTime = new Date()" >
                          <span style="margin-left: 9px;">{{ getColumnLabel(item)}}</span>
                        </li>
                      </ul >
                    </div>


                </div>
                <div class="clear"></div>

              </div>


          </div>
          <div class="modal-footer">
            <button type="button" v-on:click="addSelectedColumns" :disabled="isAddDataSourceDisabled" class="btn btn-primary">Anwenden</button>
          </div>
        </div>
      </div>
    </div>

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

input.form-control.folder{
    display: inline-block;
    width: calc(100% - 280px);
}

input.form-control , select.form-control {
    display: inline-block;
    width: calc(100% - 170px);
}

input.form-control[type=password] {
    text-align: left;
    margin: 0;
    padding-left: 12px;
}

label.item-title{
  width: 170px;
}

label.item-title2{
  width: 270px;
}

.sub-item-container{
  margin: 10px 10px;
  padding: 10px;
  border: 1px solid rgba(0,0,0,.125);
  border-radius: 5px;
  height: calc(100% - 60px);
}

.sub-item{
  float: left;
  width: 350px;
  margin: 10px 10px;
  padding: 10px;
  border: 1px solid rgba(0,0,0,.125);
  border-radius: 5px;
  font-size: 14px;
  height: calc(100% - 20px);
}

.sub-item-list{
  height: calc(100% - 50px);
  overflow: auto;
  padding: 6px;
      margin-top: 5px;
}

.sub-item input.search{
  padding: 3px;
  font-size: 12px;
  width: 150px;
  border: 1px solid #ced4da;
  border-radius: .25rem;
  margin-right: 10px;
}

.sub-item label.top{
  margin-right: 5px;
}

.sub-item input.search-checkall{

  margin-left: 10px;
}



#datasourceDialog .modal-body{
  height: calc(100vh - 350px);
}

#datasourceDialog .modal-footer{
    background: #4976b94a;
    color: #f7f7e5;
}

.workbook-item {
    width: 400px;
    max-height: 400px;
    height: 400px;
    float: left;
    margin: 5px;
}

.workbook-item .card-body {
    height: 300px;
    max-height: 300px;
    overflow: auto;
    padding: 0;
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
import router from '../../../../router'
import $ from 'jquery'

export default {
    name: 'NewConnection',
    data () {
        return {
          selectedConnectionId: false,
          selectedTable: false,
          connectionList:[],
          tableList:[],
          columnList:[],
          filterTableText: "",
          filterColumnText: "",
          errmessage: "",
          okmessage: "",
          id: false,
          isSelectSourceDialogVisible: false,
          currentUpdateDateTime: new Date(),
          workbookDataSourceList: []
        }
    },
    props:["inBox", "clone"],
    computed: {
      saveButtonName: function (){
        return this.isNew() ? "Erstellen" : "Speiren"
      },
      filterTableList: function (){
        this.currentUpdateDateTime
        return this.filterTableText == "" ? this.tableList : this.tableList.filter(item => item.includes(this.filterTableText))
      },
      filterColumnList: function (){
        this.currentUpdateDateTime
        return this.filterColumnText == "" ? this.columnList : this.columnList.filter(item => item.name.includes(this.filterColumnText))
      },
      selectedColumnList: function (){
        this.currentUpdateDateTime
        return this.filterColumnList.filter(item => item.selected)
      },
      isAddDataSourceDisabled: function (){
        this.currentUpdateDateTime
        console.log("selectedColumnList", this.selectedColumnList)
        return this.selectedColumnList.length == 0
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

            this.columnList = []
            this.tableList = []
            this.connectionList = data;

            }).catch(error => {
              console.error("There was an error!", error);
            })
      },
      loadTables(){

          dataSettingsService.readConnectionTables(this.selectedConnectionId).then(async response => {
            const data = await response.json();
            if (!response.ok) {
              const error = (data && data.errmessage) || response.statusText;
              return Promise.reject(error);
            }

            this.columnList = []
            this.tableList = data

          }).catch(error => {
            console.error("There was an error!", error);
          })
      },
      loadTableColumns(tableName){
          this.selectedTable = tableName
          dataSettingsService.readTableColumns(this.selectedConnectionId, this.selectedTable).then(async response => {
            const data = await response.json();
            if (!response.ok) {
              const error = (data && data.errmessage) || response.statusText;
              return Promise.reject(error);
            }

            this.columnList = data
            for(var i in this.columnList){
                this.columnList[i].selected = false
            }

          }).catch(error => {
            console.error("There was an error!", error);
          })
      },
      onConnectionChange(){
        this.loadTables()
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
      testConnection(){
          this.okmessage = "";
          this.errmessage = "";
          dataSettingsService.testConnection(this.connection).then(async response => {
            const data = await response.json();
            console.log("Test Connection", data);
            if (!response.ok) {
              const error = (data && data.errmessage) || response.statusText;
              this.errmessage = data.message;
              return Promise.reject(error);
            }
            this.okmessage = "Die Verbindung war erfolgreich";
          }).catch(error => {
            console.error("There was an error!", error);
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
      selectAllColumns(event){

        for(var i in this.columnList){
            this.columnList[i].selected = event.target.checked
        }
        this.currentUpdateDateTime = new Date()
      },
      addSelectedColumns(){
        var conn = this.connectionList.filter(item => item.id == this.selectedConnectionId)
        var item = {"connection": conn[0], "table": this.selectedTable, "columns": this.selectedColumnList}
        this.workbookDataSourceList.push(item)
        this.isSelectSourceDialogVisible = false
        this.currentUpdateDateTime = new Date()

        $('#datasourcestab').tab('show')

      },
      getColumnLabel(item){
        return item.name + " : " + item.type + "(" + item.size + ")"
      },
      showSelectSourceDialog(){
        this.selectedConnectionId = false
        this.columnList = []
        this.tableList = []
        this.currentUpdateDateTime = new Date()
        this.isSelectSourceDialogVisible = true
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

