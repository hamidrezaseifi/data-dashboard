<template>

    <div class="modal fade " id="datasourceDialog" v-bind:class="{ show: getSelectSourceDialogVisible }" v-if="getSelectSourceDialogVisible" style="display: block;"
         tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
      <div class="modal-dialog modal-dialog-centered" role="document" style="width: 820px; max-width: 820px;">
        <div class="modal-content" style="height: calc(100vh - 200px);">
          <div class="modal-header">
            <h5 class="modal-title" id="exampleModalLongTitle">Datenquelle außwählen ..</h5>
            <button type="button" class="close" data-dismiss="modal" aria-label="Close" v-on:click="closeDialog">
              <span aria-hidden="true">&times;</span>
            </button>
          </div>
          <div class="modal-body">
              <div class="form-group">
                  <label for="connectionlist" class="item-title">Name</label>
                  <input class="form-control" id="datasourcename" v-model="dataSource.name" />
              </div>
              <div class="form-group">
                  <label for="connectionlist" class="item-title">Verbindung-Liste</label>
                  <select class="form-control" id="connectionlist" @change="onConnectionChange($event)" v-model="dataSource.connectionId" name="selectedConnectionId">
                      <option v-for="item in getConnectionList" :key="item.id" v-bind:value="item.id" >{{item.name}}</option>
                  </select>
              </div>
              <div class="sub-item-container" v-if="dataSource.connectionId" >
                <div class="sub-item">

                    <label class="top" style="width:80px" for="sourcetype">Quelle-Typ</label>
                    <select class="sourcetype"  id="sourcetype" @change="onSourceTypeChange($event)" v-model="dataSource.dataSourceType">
                        <option value="TABLE" >Tabelle</option>
                        <option value="QUERY" >Query</option>
                    </select>
                    <br>
                    <label class="top" v-if="dataSource.dataSourceType == 'TABLE'" style="width:80px">Tabelle-Liste</label>
                    <input class="search" v-if="dataSource.dataSourceType == 'TABLE'" id="keyfilterTableText" placeholder="" v-model="filterTableText">

                    <div  class="sub-item-list tables" v-if="dataSource.dataSourceType == 'TABLE'">
                        <ul  class="list-group">
                            <li class="list-group-item" style="cursor: pointer;" v-bind:class="{active : item === dataSource.table}"
                                v-on:click="loadTableColumns(item)" v-for="item in filterTableList" :key="item" >{{item}}</li>
                        </ul>
                    </div>

                    <div  class="sub-item-list query" v-if="dataSource.dataSourceType == 'QUERY'">
                        <textarea class="query" v-model="dataSource.query" ></textarea>
                        <button type="button" :disabled="isRetrieveQueryColumnsDisabled" class="btn btn-secondary retrieve-columns-button" v-on:click="retrieveQueryColumns" >Spalten abrufen</button>
                    </div>

                </div>
                <div class="sub-item">

                    <label class="top">Spalten-Liste</label>
                    <input class="search" id="keyfiltercolumnText" placeholder="" v-model="filterColumnText">
                    <label class="top">Alle</label>
                    <input class="search-checkall" type="checkbox" v-on:click="selectAllColumns($event)">
                    <div class="sub-item-list columns">
                      <ul  class="list-group">
                        <li class="list-group-item" v-for="item in filterColumnList" :key="item.columnName" >
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

.sub-item-container{
  margin: 10px 10px;
  padding: 10px;
  border: 1px solid rgba(0,0,0,.125);
  border-radius: 5px;
  height: calc(100% - 110px);
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
  overflow: auto;
  padding: 6px;
  margin-top: 5px;
  border-top: 1px solid #ced4da;
}

.sub-item-list.tables{
  height: calc(100% - 70px);
}

.sub-item-list.query{
  height: calc(100% - 17px);
}

.sub-item-list.columns{
  height: calc(100% - 40px);
}




.sub-item input.search, .sub-item select.sourcetype{
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

label.radio-label{
    margin-left: 10px;
    margin-right: 5px;
}


textarea.query {
    width: 100%;
    max-width: 100%;
    min-width: 100%;
    height: calc(100% - 50px);
    max-height: calc(100% - 50px);
    min-height: calc(100% - 50px);
    border: 1px solid #ced4da;
}

.retrieve-columns-button{
    width: 150px !important;
    padding: 3px !important;
    margin-left: calc(100% - 150px) !important;

}
</style>
<script>
import { dataSettingsService } from '../../services/datasettings.service';

export default {
    name: 'NewConnection',
    data () {
        return {
          tableList:[],
          columnList:[],
          filterTableText: "",
          filterColumnText: "",
          errmessage: "",
          okmessage: "",
          id: false,
          dataSource: {"name": "", "connectionId": false, "connectionName" : "", "dataSourceType": "TABLE", "query": "", "table": false, "columns": []},
          currentUpdateDateTime: new Date()
        }
    },
    props:["connections", "isDialogVisible"],
    watch: {
      isDialogVisible: {
        immediate: true,
        handler (val, oldVal) {
          if(val && !oldVal){
            this.dataSource =  {"name": "", "connectionId": false, "connectionName" : "", "dataSourceType": "TABLE", "query": "", "table": false, "columns": []}
            this.tableList = []
            this.columnList = []

            this.filterTableText = ""
            this.filterColumnText = ""
          }
        }
      }
    },
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
        return this.filterColumnText == "" ? this.columnList : this.columnList.filter(item => item.columnName.includes(this.filterColumnText))
      },
      selectedColumnList: function (){
        this.currentUpdateDateTime
        return this.filterColumnList.filter(item => item.selected)
      },
      isAddDataSourceDisabled: function (){
        this.currentUpdateDateTime

        return this.selectedColumnList.length == 0 || this.dataSource.name.trim().length == 0
      },
      isRetrieveQueryColumnsDisabled: function (){
        this.currentUpdateDateTime

        return this.dataSource.query.trim().length < 10
      },
      getSelectSourceDialogVisible: function (){
        this.currentUpdateDateTime
        return this.isDialogVisible
      },
      getConnectionList: function (){
        this.currentUpdateDateTime

        return this.connections
      }
    },
    methods: {
      loadTables(){

          dataSettingsService.readConnectionTables(this.dataSource.connectionId).then(async response => {
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
          this.dataSource.table = tableName
          dataSettingsService.readTableColumns(this.dataSource.connectionId, this.dataSource.table).then(async response => {
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
      onSourceTypeChange(){
        this.columnList = []
        this.filterColumnText = ""
        this.currentUpdateDateTime = new Date()
      },
      retrieveQueryColumns(){

          var request = {"connectionId": this.dataSource.connectionId, "query": this.dataSource.query}

          dataSettingsService.readQueryColumns(request).then(async response => {
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
      selectAllColumns(event){

        for(var i in this.columnList){
            this.columnList[i].selected = event.target.checked
        }
        this.currentUpdateDateTime = new Date()
      },
      addSelectedColumns(){
        var conn = this.connections.filter(item => item.id == this.dataSource.connectionId)
        this.dataSource.connectionName = conn[0].name
        this.dataSource.columns = this.selectedColumnList

        this.$emit('sourceSelected', this.dataSource)

      },
      getColumnLabel(item){
        return item.columnName + " : " + item.columnType + "(" + item.size + ")"
      },
      closeDialog(){
        this.$emit('close')
      }

    },
    created () {


    }
};
</script>

