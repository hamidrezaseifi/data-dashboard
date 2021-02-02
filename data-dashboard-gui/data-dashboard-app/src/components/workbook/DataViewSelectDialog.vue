<template>

    <div class="modal fade " id="dataViewDialog" v-bind:class="{ show: getDialogVisible }" v-if="getDialogVisible" style="display: block;"
         tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
      <div class="modal-dialog modal-dialog-centered modal-dialog-dataview" role="document">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title" id="exampleModalLongTitle">Datenansicht erstellen ..</h5>
            <button type="button" class="close" data-dismiss="modal" aria-label="Close" v-on:click="closeDialog">
              <span aria-hidden="true">&times;</span>
            </button>
          </div>
          <div class="modal-body">
              <div class="form-group">
                  <label for="dataviewname" class="item-title">Name</label>
                  <input class="form-control" id="dataviewname" v-model="dataView.name" />
              </div>

              <h5>Datenansicht-Inhalt</h5>
              <div class="dataview-container" >
                  <div class="dataview-datasourcelist-container">
                      <ul class="list-group">
                          <li class="list-group-item" v-for="item in dataSources" :key="item.id" v-bind:value="item.id" >
                              <span class="datasource-title">{{item.name}}</span>
                              <img class="datasource-addbutton" src="@/assets/images/add.png" v-on:click="addDataSource(item)" />
                          </li>
                      </ul>

                  </div>
                  <div class="dataview-design-container">

                      <div class="card selected-datasource-item" v-for="item in selectedDataSources" :key="item.id">
                          <div class="card-header">
                              <div>
                                  <span style="display: inline-block; float: left;"><b>{{item.name}}</b></span>
                                  <button type="button" class="close" aria-label="Close" style="float: right; margin-top: -3px;" v-on:click="removeSelectedDataSource(item)" title="Löschen">
                                      <span aria-hidden="true">&times;</span>
                                  </button>
                                  <input style="float: right; margin-right: 5px;" type="checkbox" v-on:click="selectAllColumn($event, item)" >
                                  <span style="display: inline-block; clear: both;"></span>
                              </div>

                          </div>
                          <div class="card-body" style="padding: 0;">
                              <ul  class="list-group">
                                  <li class="list-group-item column-item" v-for="column in item.columns" :key="column.name" >
                                      <span v-bind:title="getColumnLabel(column)">{{ getColumnLabel(column)}}</span>
                                      <input type="checkbox" v-bind:checked="isColumnSelected(column)" v-on:click="selectColumn($event, column)" >
                                  </li>
                              </ul >

                          </div>
                      </div>


                  </div>
                  <div class="dataview-columns-container">
                      <ul class="list-group">
                          <li class="list-group-item" v-for="item in dataView.columns" :key="item.id" v-bind:value="item.id" >
                              <div style="display: block" class="">{{item.sourceColumn.columnName}}</div>
                              <label style="margin-right: 4px;" v-bind:for="'chkuseSelect' + item.sourceColumn.id">Als Egebnis</label>
                              <input v-bind:id="'chkuseSelect' + item.sourceColumn.id" style="margin-right: 9px;" type="checkbox" v-model="item.useAsSelect">
                              <label style="margin-right: 4px;">Egebnis Typ</label>
                              <select v-model="item.selectType">
                                  <option value="NORMAL">Normal</option>
                                  <option value="COUNT">Count</option>
                                  <option value="MAX">Max</option>
                                  <option value="MIN">Min</option>
                                  <option value="DISTINCT">distinct</option>
                              </select>
                          </li>
                      </ul>

                  </div>
                  <div class="clear"></div>
              </div>


          </div>
          <div class="modal-footer">
              <span style="display: inline-block; width: 50%; max-height: 50px; overflow: auto; color: black;">{{dataView}}</span>
            <button type="button" v-on:click="doneSelectedView" :disabled="isAddDataViewDisabled" class="btn btn-primary">Anwenden</button>
          </div>

        </div>
      </div>


    </div>
</template>

<style>

.modal-dialog.modal-dialog-dataview{
    width: calc(100% - 100px) !important;
    max-width: calc(100% - 100px) !important;
}

.modal-dialog.modal-dialog-dataview .modal-content{
    height: calc(100vh - 50px) !important;
}

.modal-dialog.modal-dialog-dataview .dataview-container{
    border: 1px solid #ced4da;
    border-radius: 4px;
    padding: 9px;
    height: calc(100% - 100px);
}

.dataview-datasourcelist-container{
    float: left;
    width: 250px;
    border: 1px solid #ced4da;
    border-radius: 4px;
    padding: 10px;
    height: calc(100% - 0px);
}

.dataview-design-container{
    float: left;
    width: calc(100% - 570px);
    margin-left: 10px;
    border: 1px solid #ced4da;
    border-radius: 4px;
    padding: 10px;
    height: calc(100% - 0px);
    font-size: 12px;
}

.modal-dialog.modal-dialog-dataview .dataview-columns-container{
    float: left;
    width: 300px;
    margin-left: 10px;
    border: 1px solid #ced4da;
    border-radius: 4px;
    padding: 10px;
    height: calc(100% - 0px);
    font-size: 12px;
}

.datasource-title{
    display: inline-block;
    float:left;
}

.datasource-addbutton{
    float:right;
    width: 20px;
    cursor: pointer;
}

.card.selected-datasource-item{
    width: 250px;
    float: left;
    margin-right: 10px;
}

.card.selected-datasource-item .card-body{
    padding: 0px;
    max-height: 500px;
    overflow: auto;
}

.card.selected-datasource-item .column-item{
    padding: 5px;
}

.card.selected-datasource-item .column-item span{
    max-width: 220px;
    display: inline-block;
    overflow:hidden;
    margin-right: 5px;
    float: left;
}

.card.selected-datasource-item .column-item input[type='checkbox']{

    float: right;
}

input.form-control {
    display: inline-block;
    width: calc(100% - 200px);
}

input.form-control , select.form-control {
    display: inline-block;
    width: calc(100% - 170px);
}

label.item-title{
  width: 170px;
}

#dataViewDialog .modal-body{
  height: calc(100vh - 350px);
}

#dataViewDialog .modal-footer{
    background: #4976b94a;
    color: #f7f7e5;
}

</style>
<script>
//import { dataSettingsService } from '../../services/datasettings.service';

import { uuidv4 } from '../../helpers/utils';

export default {
    name: 'DataViewSelectDialog',
    data () {
        return {
          errmessage: "",
          okmessage: "",
          selectedDataSources: [],
          dataView:  {"id": uuidv4(), "name": "", "connectionId": false, "connectionName" : "", "dataSourceType": "TABLE", "query": "", "table": false, "columns": []},
          currentUpdateDateTime: new Date()
        }
    },
    props:["dataSources", "isDialogVisible"],
    watch: {
      isDialogVisible: {
        immediate: true,
        handler (val, oldVal) {
          if(val && !oldVal){
            this.dataView =  {"id": uuidv4(), "name": "", "connectionId": false, "connectionName" : "", "dataSourceType": "TABLE", "query": "", "table": false, "columns": []}

          }
        }
      }
    },
    computed: {
      saveButtonName: function (){
        return this.isNew() ? "Erstellen" : "Speiren"
      },
      getDialogVisible: function (){
        this.currentUpdateDateTime
        return this.isDialogVisible
      },
      isAddDataViewDisabled: function (){
        this.currentUpdateDateTime

        return this.dataView.columns.length == 0 || this.dataView.name.trim().length == 0
      }
    },
    methods: {
      addDataSource(item){
        this.selectedDataSources.push(item)
      },
      doneSelectedView(){
        this.$emit('dataViewSelected', this.dataView)

      },
      getColumnLabel(item){
        return item.columnName + " : " + item.columnType
      },
      removeSelectedDataSource(removeItem){
        this.selectedDataSources = this.selectedDataSources.filter(item => item.id != removeItem.id)
      },
      isColumnSelected(searchItem){
        var found = this.dataView.columns.filter(item => item.sourceColumn.id == searchItem.id)
        return found.length > 0;
      },
      selectColumn(event, column){
        if(event.target.checked){
            this.dataView.columns.push({"sourceColumn": column, "useAsSelect": true, "selectType": "NORMAL"});
        }
        else{
            this.dataView.columns = this.dataView.columns.filter(item => item.id != column.id)
        }

      },
      selectAllColumn(event, selectedItem){
        var idList = []
        for(var i in selectedItem.columns){
            idList.push(selectedItem.columns[i].id)
        }
        this.dataView.columns = this.dataView.columns.filter(item => idList.indexOf(item.sourceColumn.id) == -1)
        if(event.target.checked){
            for(i in selectedItem.columns){
                this.selectColumn(event, selectedItem.columns[i])
            }
        }
      },
      closeDialog(){
        this.$emit('close')
      }

    },
    created () {

    }
};
</script>

