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
                <label for="connectionlist" class="item-title">Verbindung-Liste</label>
                <select class="form-control" id="connectionlist" @change="onConnectionChange($event)" v-model="selectedConnectionId" name="selectedConnectionId">
                  <option v-for="item in getConnectionList" :key="item.id" v-bind:value="item.id" >{{item.name}}</option>
                </select>
              </div>
              <div class="sub-item-container" >
                <div class="sub-item">

                    <label class="top">Tabelle-Liste</label>
                    <input class="search" id="keyfilterTableText" placeholder="" v-model="filterTableText">
                    <div  class="sub-item-list">
                      <ul  class="list-group">
                        <li class="list-group-item" style="cursor: pointer;" v-bind:class="{active : item === selectedTable}"
                                v-on:click="loadTableColumns(item)" v-for="item in filterTableList" :key="item" >{{item}}</li>
                      </ul>
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

</style>
<script>
import { dataSettingsService } from '../../services/datasettings.service';

export default {
    name: 'NewConnection',
    data () {
        return {
          selectedConnectionId: false,
          selectedTable: false,
          tableList:[],
          columnList:[],
          filterTableText: "",
          filterColumnText: "",
          errmessage: "",
          okmessage: "",
          id: false,
          currentUpdateDateTime: new Date()
        }
    },
    props:["connectionList", "isDialogVisible"],
    watch: {
      isDialogVisible: {
        immediate: true,
        handler (val, oldVal) {
          if(val && !oldVal){
            this.tableList = []
            this.columnList = []
            this.selectedTable = false
            this.selectedConnectionId = false
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
        return this.filterColumnText == "" ? this.columnList : this.columnList.filter(item => item.name.includes(this.filterColumnText))
      },
      selectedColumnList: function (){
        this.currentUpdateDateTime
        return this.filterColumnList.filter(item => item.selected)
      },
      isAddDataSourceDisabled: function (){
        this.currentUpdateDateTime

        return this.selectedColumnList.length == 0
      },
      getSelectSourceDialogVisible: function (){
        this.currentUpdateDateTime
        return this.isDialogVisible
      },
      getConnectionList: function (){
        this.currentUpdateDateTime

        return this.connectionList
      }
    },
    methods: {
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
      selectAllColumns(event){

        for(var i in this.columnList){
            this.columnList[i].selected = event.target.checked
        }
        this.currentUpdateDateTime = new Date()
      },
      addSelectedColumns(){
        var conn = this.connectionList.filter(item => item.id == this.selectedConnectionId)
        var item = {"connection": conn[0], "table": this.selectedTable, "columns": this.selectedColumnList}
        this.$emit('sourceSelected', item)

      },
      getColumnLabel(item){
        return item.name + " : " + item.type + "(" + item.size + ")"
      },
      closeDialog(){
        this.$emit('close')
      }

    },
    created () {


    }
};
</script>

