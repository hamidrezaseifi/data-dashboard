<template>

    <div class="modal fade " id="dataPresentationDialog" v-bind:class="{ show: getDialogVisible }" v-if="getDialogVisible" style="display: block;"
         tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
      <div class="modal-dialog modal-dialog-centered modal-dialog-presentation" role="document">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title" id="exampleModalLongTitle">Präsentation erstellen ..</h5>
            <button type="button" class="close" data-dismiss="modal" aria-label="Close" v-on:click="closeDialog">
              <span aria-hidden="true">&times;</span>
            </button>
          </div>
          <div class="modal-body">
              <div class="form-group">
                  <label for="presentationname" class="item-title">Name</label>
                  <input class="form-control" id="presentationname" v-model="presentation.name" />
              </div>
              <div class="form-group">
                  <label for="dataSourceTypesselect" class="item-title">Datenquellentyp</label>
                  <select id="dataSourceTypesselect" class="form-control" v-model="presentation.dataSourceType">
                      <option>--- Datenquellentyp außwehlen ---</option>
                      <option v-for="item in dataSourceTypes" :key="item.id" v-bind:value="item.id">{{item.title}}</option>
                  </select>
              </div>
              <div class="form-group" v-if="presentation.dataSourceType === 'dataview'">
                  <label for="dataviewselect" class="item-title">Datenansicht</label>
                  <select id="dataviewselect" class="form-control" v-on:click="selectDataView($event)" v-model="presentation.dataSourceId">
                      <option>--- Datenansicht außwehlen ---</option>
                      <option v-for="item in dataViews" :key="item.id" v-bind:value="item.id">{{item.name}}</option>
                  </select>
              </div>
              <div class="form-group" v-if="presentation.dataSourceType === 'filter'">
                  <label for="filterselect" class="item-title">Filter</label>
                  <select id="filterselect" class="form-control" v-on:click="selectFilter($event)" v-model="presentation.dataSourceId">
                      <option>--- Filter außwehlen ---</option>
                      <option v-for="item in filters" :key="item.id" v-bind:value="item.id">{{item.name}}</option>
                  </select>
              </div>
              <div class="form-group">
                  <label for="presentationTypeselect" class="item-title">Präsentationstyp</label>
                  <select id="presentationTypeselect" class="form-control" v-model="presentation.presentationType">
                      <option>--- Präsentationstyp außwehlen ---</option>
                      <option v-for="item in presentationTypes" :key="item.id" v-bind:value="item.id">{{item.title}}</option>
                  </select>
              </div>
              <div class="form-group">
                  <label for="presentationStyleselect" class="item-title">Präsentationsstil</label>
                  <select id="presentationStyleselect" class="form-control" v-model="presentation.presentationStyle">
                      <option>--- Präsentationstyp außwehlen ---</option>
                      <option v-for="item in presentationStyles" :key="item" v-bind:value="item">{{item}}</option>
                  </select>
              </div>

              <div class="form-group" style="height: calc(100% - 300px);" v-if="(presentation.presentationType==='label') && isDataViewSelected">
                  <label for="presentationTypeLabelText" class="item-title">Etiket-Text</label>
                  <div class="clear"></div>
                  <div class="form-group selecting-dataview-column-container" v-if="isDataViewSelected">
                      <div class="selecting-dataview-column" v-for="item in presentation.dataView.columns" :key="item.label" v-on:click="addColumnToLabel(item.label)">
                          {{item.label}}
                      </div>

                  </div>
                  <textarea id="presentationTypeLabelText" class="presentation-label-text" v-model="presentationLabelText">

                  </textarea>

              </div>


          </div>
          <div class="modal-footer">

            <button type="button" v-on:click="doneSelectedPresentation" :disabled="isAddPresentationDisabled" class="btn btn-primary">Anwenden</button>
          </div>

        </div>
      </div>


    </div>
</template>

<style>

.modal-dialog.modal-dialog-presentation{
    width: calc(100% - 100px) !important;
    max-width: calc(100% - 100px) !important;
}

.modal-dialog.modal-dialog-presentation .modal-content{
    height: calc(100vh - 50px) !important;
}

.modal-dialog.modal-dialog-presentation .dataview-container{
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

#dataPresentationDialog .modal-body{
  height: calc(100vh - 350px);
}

#dataPresentationDialog .modal-footer{
    background: #4976b94a;
    color: #f7f7e5;
}

.selecting-dataview-column{
    display: inline-block;
    padding: 5px;
    background: #a4e0b452;
    cursor: pointer;
    margin: 5px;
    border: 1px solid gray;
    font-size: 12px;
    border-radius: 4px;
}

.selecting-dataview-column-container{
    border: 1px solid rgb(206, 212, 218);
    border-radius: 3px;
    padding: 5px;
    width: 250px;
    float: left;
    height: calc(100%);
}

.presentation-label-text{
    width: calc(100% - 260px);
    float: left;
    margin-left: 10px;
    height: calc(100%);
}

</style>
<script>
//import { dataSettingsService } from '../../services/datasettings.service';

import { uuidv4 } from '../../helpers/utils';
import $ from 'jquery'

export default {
    name: 'DataViewSelectDialog',
    data () {
        return {
          errmessage: "",
          okmessage: "",
          selectedDataView: false,
          presentation:  {"id": uuidv4(), "name": "", "dataSourceType": "", "dataSourceId": false, "presentationType": "", "presentationStyle": "", "properties": []},
          currentUpdateDateTime: new Date(),
          presentationLabelText : ""
        }
    },
    props:["dataViews", "filters", "isDialogVisible", "presentationTypes", "presentationStyles", "dataSourceTypes"],
    watch: {
      isDialogVisible: {
        immediate: true,
        handler (val, oldVal) {

          if(val && !oldVal){
            this.presentation =  {"id": uuidv4(), "name": "", "dataSourceType": "", "dataSourceId": false, "presentationType": "", "presentationStyle": "", "properties": []}
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
      isAddPresentationDisabled: function (){
        this.currentUpdateDateTime

        return this.presentation.dataView == false || this.presentation.name.trim().length == 0 || this.presentation.presentationType == "" || this.presentation.presentationStyle == ""
      },
      isDataViewSelected: function (){
        this.currentUpdateDateTime

        return this.selectedDataView
      },


    },
    methods: {
      selectDataView(event){
        var id = event.target.value;
        for(var i in this.dataViews){
            if(this.dataViews[i].id == id){
                this.selectedDataView = this.dataViews[i]
                this.currentUpdateDateTime = new Date()
                return
            }
        }

      },
      selectFilter(event){
        var id = event.target.value;
        for(var i in this.filters){
            if(this.filters[i].id == id){
                this.selectedFilter = this.filters[i]
                this.currentUpdateDateTime = new Date()
                return
            }
        }

      },
      doneSelectedPresentation(){

        if(this.presentation.presentationType==='label'){
            this.presentation.properties.push({"name": "label_text", "value": this.presentationLabelText})
        }
        this.$emit('presentationSelected', this.presentation)

      },
      getColumnLabel(item){
        return item.columnName + " : " + item.columnType
      },
      isSelectedDataViewId(id){
        this.currentUpdateDateTime

        return this.presentation.dataView ? id == this.presentation.dataView.Id : false
      },
      closeDialog(){
        this.$emit('close')
      },
      addColumnToLabel(column){
        //presentationTypeLabelText

        var cursorPos = $('#presentationTypeLabelText').prop('selectionStart');
        var v = $('#presentationTypeLabelText').val();
        var textBefore = v.substring(0,  cursorPos);
        var textAfter  = v.substring(cursorPos, v.length);

        $('#presentationTypeLabelText').val(textBefore + "{{" + column + "}}" + textAfter);

      }

    },
    created () {

    }
};
</script>

