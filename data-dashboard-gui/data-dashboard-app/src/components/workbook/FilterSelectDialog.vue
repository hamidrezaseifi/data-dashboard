<template>

    <div class="modal fade " id="dataFilterDialog" v-bind:class="{ show: getDialogVisible }" v-if="getDialogVisible" style="display: block;"
         tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
      <div class="modal-dialog modal-dialog-centered modal-dialog-filter" role="document">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title" id="exampleModalLongTitle">Filter erstellen ..</h5>
            <button type="button" class="close" data-dismiss="modal" aria-label="Close" v-on:click="closeDialog">
              <span aria-hidden="true">&times;</span>
            </button>
          </div>
          <div class="modal-body">
              <div class="form-group">
                  <label for="datafiltername" class="item-title">Name</label>
                  <input class="form-control" id="datafiltername" v-model="filter.name" />
              </div>
              <div class="form-group">
                  <label for="dataviewselect" class="item-title">Datenansicht</label>
                  <select id="dataviewselect" class="form-control" v-on:click="selectDataView($event)">
                      <option>--- Datenansicht außwehlen ---</option>
                      <option v-for="item in dataViews" :key="item.id" v-bind:value="item.id">{{item.name}}</option>
                  </select>

              </div>
              <div class="form-group">
                  <label for="filtertypeselect" class="item-title">Filter-Sicht-Typ</label>
                  <select id="filtertypeselect" class="form-control" v-model="filter.filterType">
                      <option value="">--- Filter-Sicht-Typ ---</option>
                      <option value="DROPDOWN">DropDown-Liste</option>
                      <option value="LIST">Liste</option>
                      <option value="TABEL">Tabelle</option>
                  </select>
              </div>
              <div class="form-group" v-if="isDataViewSelected">
                  <label for="filterkeyselect" class="item-title">Filter-Schlüssel</label>
                  <select id="filterkeyselect" class="form-control" v-model="filter.filterKey">
                      <option value="">--- Filter-Schlüssel ---</option>
                      <option v-for="item in filter.dataView.columns" :key="item.sourceColumn.columnName">{{item.label}}</option>
                  </select>
              </div>

          </div>
          <div class="modal-footer">

            <button type="button" v-on:click="doneSelectedFilter" :disabled="isAddFilterDisabled" class="btn btn-primary">Anwenden</button>
          </div>

        </div>
      </div>


    </div>
</template>

<style>

.modal-dialog.modal-dialog-filter{
    width: calc(100% - 100px) !important;
    max-width: calc(100% - 100px) !important;
}

.modal-dialog.modal-dialog-filter .modal-content{
    height: calc(100vh - 50px) !important;
}

.modal-dialog.modal-dialog-filter .dataview-container{
    border: 1px solid #ced4da;
    border-radius: 4px;
    padding: 9px;
    height: calc(100% - 100px);
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

.form-group select{

}

#dataFilterDialog .modal-body{
  height: calc(100vh - 350px);
}

#dataFilterDialog .modal-footer{
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
          filter:  {"id": uuidv4(), "name": "", "dataView": false, "dataViewName" : "", "filterType": "LIST", "filterKey": false},
          currentUpdateDateTime: new Date()
        }
    },
    props:["dataViews", "isDialogVisible"],
    watch: {
      isDialogVisible: {
        immediate: true,
        handler (val, oldVal) {

          if(val && !oldVal){
            this.filter =  {"id": uuidv4(), "name": "", "dataView": false, "dataViewName" : "", "filterType": "LIST", "filterKey": false}
          }
        }
      }
    },
    computed: {
      saveButtonName: function (){
        return this.isNew() ? "Erstellen" : "Speichren"
      },
      getDialogVisible: function (){
        this.currentUpdateDateTime
        return this.isDialogVisible
      },
      isAddFilterDisabled: function (){
        this.currentUpdateDateTime

        return this.filter.dataView == false || this.filter.name.trim().length == 0 || this.filter.filterType == "" || this.filter.filterKey == false
      },
      isDataViewSelected: function (){
        this.currentUpdateDateTime

        return this.filter.dataView != false
      },


    },
    methods: {
      selectDataView(event){
        var id = event.target.value;
        for(var i in this.dataViews){
            if(this.dataViews[i].id == id){
                this.filter.dataView = this.dataViews[i]
                this.currentUpdateDateTime = new Date()
            }
        }

      },
      doneSelectedFilter(){
        this.$emit('filterSelected', this.filter)

      },
      getColumnLabel(item){
        return item.columnName + " : " + item.columnType
      },
      isSelectedDataViewId(id){
        this.currentUpdateDateTime

        return this.filter.dataView ? id == this.filter.dataView.Id : false
      },
      closeDialog(){
        this.$emit('close')
      }

    },
    created () {

    }
};
</script>

