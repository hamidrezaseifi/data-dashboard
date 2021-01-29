<template>
  <div class="newworkbook">

    <div class="card newworkbook">
      <div class="card-header">

        <b>New Workbook</b>
        <button type="button" class="btn" v-on:click="reloadData"><img src="@/assets/images/refresh.svg" width="20" /></button>
        <router-link class="" to="/data/settings/connections"><img src="@/assets/images/card-list.svg" width="20" /></router-link>
      </div>
      <div class="card-body">
        <form id="newconnectionform" @submit.prevent="handleSaveData">
          <div class="form-group">
            <label for="connectionlist" class="item-title">Verbindung-Liste</label>
            <select class="form-control" id="connectionlist" @change="onConnectionChange($event)" v-model="selectedConnectionId" name="selectedConnectionId">
              <option v-for="item in connectionList" :key="item.id" v-bind:value="item.id" >{{item.name}}</option>
            </select>
          </div>
          <div class="card">
            <div class="card sub-item">
              <div class="form-group">
                <label class="item-title">Tabelle-Liste</label>
                <input class="form-control" id="keyfilterTableText" placeholder="" v-model="filterTableText">
                <div style="height: 300px; overflow: auto; padding: 6px; ">
                  <div class="list-group">
                    <button type="button" class="list-group-item list-group-item-action" v-on:click="selectedTable=item" v-for="item in filterTableList" :key="item" >{{item}}</button>
                  </div>
                </div>
              </div>

            </div>
            <div class="card sub-item">
              <div class="form-group">
                <label class="item-title">Tabelle-Liste</label>
                <input class="form-control" id="keyfiltercolumnText" placeholder="" v-model="filterTableText">
                <div style="height: 300px; overflow: auto; padding: 6px; ">
                  <div class="list-group">
                    <button type="button" class="list-group-item list-group-item-action" v-on:click="selectedColumn=item" v-for="item in filterColumnList" :key="item" >{{item}}</button>
                  </div>
                </div>
              </div>

            </div>

          </div>

          <div class="form-group">
            <span v-if="errmessage != ''" class="alert alert-danger" style="float:left; max-width: calc(100% - 300px);" v-html="errmessage"></span>
            <span v-if="okmessage != ''" class="alert alert-success" style="float:left; max-width: calc(100% - 300px);" v-html="okmessage"></span>

            <button type="button" class="btn btn-secondary actionbutton" v-on:click="testConnection" style="float:right;">Test</button>

            <button type="submit" class="btn btn-primary actionbutton" style="float:right;">{{saveButtonName}}</button>
          </div>
        </form>

      </div>
    </div>
    {{selectedTable}}
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

.card.sub-item{
  float: left;
  width: 400px;
  margin: 10px 10px;
  padding: 10px;
}

</style>
<script>
import { dataSettingsService } from '../../../../services/datasettings.service';
import router from '../../../../router'

export default {
    name: 'NewConnection',
    data () {
        return {
          selectedConnectionId: false,
          selectedTable: false,
          connectionList:[],
          tableList:[],
          filterTableText: "",
          errmessage: "",
          okmessage: "",
          id: false
        }
    },
    props:["inBox", "clone"],
    computed: {
      saveButtonName: function (){
        return this.isNew() ? "Erstellen" : "Speiren"
      },
      filterTableList: function (){
        var vm = this
        console.log("filter" , vm.filterTableText)
        console.log("includes" , "beh_drg_union_dezile_beh_kap".includes(vm.filterTableText))
        return this.filterTableText == "" ? this.tableList : this.tableList.filter(item => item.includes(this.filterTableText))
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

            this.tableList = data

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
      }

    },
    created () {
      this.loadConnections()

    }
};
</script>

