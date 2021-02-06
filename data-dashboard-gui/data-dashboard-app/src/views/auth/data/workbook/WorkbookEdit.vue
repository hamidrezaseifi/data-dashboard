<template>
  <div class="newworkbook">

    <div class="card newworkbook">
      <div class="card-header">

        <b>{{pageTitle}}</b>

        <router-link class="toolbar-item" to="/data/settings/workbook/list"><img src="@/assets/images/card-list.svg" width="20" /></router-link>
      </div>
      <div class="card-body">

        <form id="newconnectionform" @submit.prevent="handleSaveData">

          <ul class="nav nav-tabs">
            <li class="nav-item">
                <a class="nav-link active" id="generaltab" data-toggle="tab" href="#generaltabcontent" role="tab" aria-controls="home" aria-selected="true">Info</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" id="datasourcestab" v-bind:class="{disabled: isDataSourceTabDisabled}" data-toggle="tab" href="#datasourcestabcontent" role="tab" aria-selected="true">Datenquelle</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" id="dataviewstab" v-bind:class="{disabled: isDataviewsTabDisabled}" data-toggle="tab" href="#dataviewstabcontent" role="tab" aria-selected="false">Datenansichten</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" id="presentationtab" v-bind:class="{disabled: isPresentationTabDisabled}" data-toggle="tab" href="#presentationtabcontent" role="tab" aria-selected="false">Präsentation</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" id="finaltab" v-bind:class="{disabled: isFinalTabDisabled}" data-toggle="tab" href="#finaltabcontent" role="tab" aria-selected="false">Finalisieren</a>
            </li>
          </ul>
          <div class="tab-content" id="myTabContent">
              <div class="tab-pane fade show active tabs-content" id="generaltabcontent" role="tabpanel" aria-labelledby="generaltab">

                  <div style="padding: 30px 20px 10px 20px; width: 60%; ">
                      <div class="form-group">
                          <h5>Workbook Info</h5>
                      </div>
                      <div class="form-group">
                          <label for="nameinput" class="item-title">Kunde</label>
                          <select class="form-control" id="customerlist" v-model="workbook.customerId">
                            <option v-for="customer in customers" v-bind:key="customer.id"  v-bind:value="customer.id" >{{customer.name}}</option>
                          </select>
                      </div>
                      <div class="form-group" v-if="workbook.customerId">
                          <label for="nameinput" class="item-title">Name</label>
                          <input class="form-control" id="nameinput" placeholder="" name="name" v-model="workbook.name">
                      </div>
                      <div class="form-group" v-if="workbook.customerId">
                          <label for="descriptioninput" class="item-title">Beschreibung</label>
                          <textarea class="form-control" id="descriptioninput" placeholder="" name="name" v-model="workbook.description"> </textarea>
                      </div>

                  </div>

              </div>
              <div class="tab-pane fade tabs-content" id="datasourcestabcontent" role="tabpanel" aria-labelledby="datasourcestab">
                  <div class="datasource-toolbar">
                      <button type="button" class="btn add-source-button" v-on:click="showSelectSourceDialog()"><img src="@/assets/images/database_add.png" width="20" /></button>
                  </div>
                  <WorkbookDataSourceItem  v-for="item in workbook.dataSources" :key="item.table"
                                           v-on:removeDataSource="removeDataSource" v-bind:workbookDataSourceItem="item">

                  </WorkbookDataSourceItem>


                  <div class="clear"></div>

              </div>
              <div class="tab-pane fade tabs-content" id="dataviewstabcontent" role="tabpanel" aria-labelledby="dataviewstab">
                  <div class="datasource-toolbar">
                      <button type="button" class="btn add-source-button" v-on:click="showSelectViewDialog()"><img src="@/assets/images/dataview.png" width="20" /></button>
                  </div>

                <WorkbookDataViewItem v-for="item in workbook.dataViews" :key="item.id" v-bind:workbookDataView="item" v-on:removeDataView="removeDataView"></WorkbookDataViewItem>

              </div>
              <div class="tab-pane fade tabs-content" id="presentationtabcontent" role="tabpanel" aria-labelledby="presentationtab">
                  <div class="datasource-toolbar">
                      <button type="button" class="btn add-source-button" style="padding: 8px; margin-left: 6px;" v-on:click="showSelectFilterDialog()"><img src="@/assets/images/filter.png" width="20" /></button>
                      <button type="button" class="btn add-source-button" style="padding: 8px;" v-on:click="showSelectPresentationDialog()"><img src="@/assets/images/presentation.png" width="20" /></button>
                  </div>

                  <WorkbookFilterItem v-for="item in workbook.filters" :key="item.id" v-bind:workbookFilter="item" v-on:removeFilter="removeFilter"></WorkbookFilterItem>

                  <WorkbookPresentationItem v-for="item in workbook.presentations" :key="item.id" v-bind:presentation="item" v-on:removePresentation="removePresentation"></WorkbookPresentationItem>
              </div>
              <div class="tab-pane fade tabs-content" id="finaltabcontent" role="tabpanel" aria-labelledby="finaltab">
                  final
              </div>
          </div>

          <div class="form-group" style="margin-top: 10px;">
            <span style="display: inline-block; width: 50%; max-height: 50px; overflow: auto;">{{workbook}}</span>
            <span v-if="errmessage != ''" class="alert alert-danger" style="float:left; max-width: calc(100% - 300px);" v-html="errmessage"></span>
            <span v-if="okmessage != ''" class="alert alert-success" style="float:left; max-width: calc(100% - 300px);" v-html="okmessage"></span>
            <button type="button" class="btn btn-primary actionbutton"  v-bind:disabled="isSaveButtonDisabled" v-on:click="saveWorkbook" style="float:right;">{{saveButtonName}}</button>
            <button type="button" class="btn btn-secondary actionbutton" style="float:right;">Test</button>
          </div>
        </form>

      </div>
    </div>

    <DataSourceSelectDialog v-bind:connections="getConnections" v-on:sourceSelected="addSelectedColumns"
                            v-bind:isDialogVisible="getSelectSourceDialogVisible" v-on:close="closeSelectSourceDialog">

    </DataSourceSelectDialog>

    <DataViewSelectDialog v-bind:isDialogVisible="getSelectViewDialogVisible" v-on:close="closeSelectViewDialog"
                          v-on:dataViewSelected="addDataView" v-bind:dataSources="getDataSources">

    </DataViewSelectDialog>

    <FilterSelectDialog v-bind:isDialogVisible="getSelectFilterDialogVisible" v-on:close="closeSelectFilterDialog"
                      v-on:filterSelected="addFilter" v-bind:dataViews="getDataViews"></FilterSelectDialog>

    <PresentationSelectDialog v-bind:isDialogVisible="getSelectPresentationDialogVisible" v-on:close="closeSelectPresentationDialog"
                      v-on:presentationSelected="addPresentation" v-bind:dataViews="getDataViews"
                      v-bind:presentationTypes="getPresentationTypes" v-bind:presentationStyles="getPresentationStyles" ></PresentationSelectDialog>



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

.newworkbook .card-header a.toolbar-item{
    margin: 0 10px;
}

.add-source-button{

}
.datasource-toolbar{
    background-color: #efefef;
}
</style>
<script>
//import { dataSettingsService } from '../../../../services/datasettings.service';
import { workbookService } from '../../../../services/workbook.service';
import DataSourceSelectDialog from '../../../../components/workbook/DataSourceSelectDialog.vue';
import WorkbookDataSourceItem from '../../../../components/workbook/WorkbookDataSourceItem.vue';
import DataViewSelectDialog from '../../../../components/workbook/DataViewSelectDialog.vue';
import WorkbookDataViewItem from '../../../../components/workbook/WorkbookDataViewItem.vue';
import FilterSelectDialog from '../../../../components/workbook/FilterSelectDialog.vue';
import WorkbookFilterItem from '../../../../components/workbook/WorkbookFilterItem.vue';
import PresentationSelectDialog from '../../../../components/workbook/PresentationSelectDialog.vue';
import WorkbookPresentationItem from '../../../../components/workbook/WorkbookPresentationItem.vue';

//import router from '../../../../router'
import $ from 'jquery'

export default {
    name: 'NewConnection',
    data () {
        return {
          connections:[],
          errmessage: "",
          okmessage: "",
          id: false,
          isSelectSourceDialogVisible: false,
          isSelectViewDialogVisible: false,
          isSelectFilterDialogVisible: false,
          idPresentationDialogVisible: false,
          currentUpdateDateTime: new Date(),
          customers: [],
          presentationTypes:[],
          presentationStyles:[],
          workbook: {"customerId": false, "dataSources": [], "dataViews": [], "filters":[], "presentations":[], "description": "", "id": false, "name" : false, }
        }
    },
    components:{
        DataSourceSelectDialog,
        WorkbookDataSourceItem,
        DataViewSelectDialog,
        WorkbookDataViewItem,
        FilterSelectDialog,
        WorkbookFilterItem,
        PresentationSelectDialog,
        WorkbookPresentationItem
    },
    props:["inBox", "clone"],
    computed: {
      customerId: {
        get () {

          return this.$store.state.authentication.loginData.customer.id
        }

      },
      pageTitle: function (){
        return this.isNew() ? "Neu Workbook" : "Workbook " + this.workbook.name + " bearbeiten"
      },
      saveButtonName: function (){
        return this.isNew() ? "Erstellen" : "Speiren"
      },
      isDataSourceTabDisabled: function (){
        this.currentUpdateDateTime
        return !this.workbook.customerId
      },
      isDataviewsTabDisabled: function (){
        this.currentUpdateDateTime
        return !this.workbook.customerId || !this.workbook.dataSources || this.workbook.dataSources.length == 0
      },
      isPresentationTabDisabled: function (){
        this.currentUpdateDateTime
        return !this.workbook.customerId || !this.workbook.dataSources || this.workbook.dataSources.length == 0
      },
      isFinalTabDisabled: function (){
        this.currentUpdateDateTime
        return !this.workbook.customerId || !this.workbook.dataSources || this.workbook.dataSources.length == 0
      },
      isSaveButtonDisabled: function (){
        this.currentUpdateDateTime

        return !this.workbook.customerId || this.workbook.dataSources.length == 0 || !this.workbook.name || this.workbook.name === null || this.workbook.name.length == 0
      },
      isAddDataSourceDisabled: function (){
        this.currentUpdateDateTime
        return this.selectedColumnList.length == 0
      },
      getSelectSourceDialogVisible: function (){
        this.currentUpdateDateTime
        return this.isSelectSourceDialogVisible
      },
      getSelectViewDialogVisible: function (){
        this.currentUpdateDateTime
        return this.isSelectViewDialogVisible
      },
      getSelectFilterDialogVisible: function (){
        this.currentUpdateDateTime

        return this.isSelectFilterDialogVisible
      },
      getSelectPresentationDialogVisible: function (){
        this.currentUpdateDateTime

        return this.idPresentationDialogVisible
      },
      getConnections: function (){
        this.currentUpdateDateTime
        return this.connections
      },
      getDataSources: function (){
        this.currentUpdateDateTime
        return this.workbook.dataSources
      },
      getDataViews: function (){
        this.currentUpdateDateTime
        return this.workbook.dataViews
      },
      getPresentationTypes: function (){
        this.currentUpdateDateTime
        return this.presentationTypes
      },
      getPresentationStyles: function (){
        this.currentUpdateDateTime
        return this.presentationStyles
      }
    },
    methods: {
      loadInitialData(){
        workbookService.newWorkbook(this.customerId).then(async response => {
            const data = await response.json();
            if (!response.ok) {
              const error = (data && data.message) || response.statusText;
              return Promise.reject(error);
            }

            this.connections = data.connections;
            this.customers = data.customers;
            this.presentationStyles = data.presentationStyles;
            this.presentationTypes = data.presentationTypes;
            this.workbook = data.workbook
            this.currentUpdateDateTime = new Date()

            }).catch(error => {
              console.error("There was an error!", error);
            })
      },
      loadWorkflowData(){
        workbookService.readWorkbook(this.customerId, this.id).then(async response => {
            const data = await response.json();
            if (!response.ok) {
              const error = (data && data.message) || response.statusText;
              return Promise.reject(error);
            }

            this.connections = data.connections;
            this.customers = data.customers;
            this.presentationStyles = data.presentationStyles;
            this.presentationTypes = data.presentationTypes;
            this.workbook = data.workbook
            this.currentUpdateDateTime = new Date()

            }).catch(error => {
              console.error("There was an error!", error);
            })
      },
      saveWorkbook () {

        var func = false;
        if(this.isEdit()){
          func = workbookService.updateWorkbook(this.customerId, this.workbook)
        }
        else{
          func = workbookService.createWorkbook(this.customerId, this.workbook)
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

          this.workbook = data;
          //router.push('/data/settings/connections');

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
          this.loadWorkflowData();
        }
        if(this.isNew()){
          this.loadInitialData()
        }
        if(this.isClone()){
          this.loadCloneData()
        }
      },
      addSelectedColumns(item){
        this.workbook.dataSources.push(item)
        this.isSelectSourceDialogVisible = false
        this.currentUpdateDateTime = new Date()

        $('#datasourcestab').tab('show')

      },
      addDataView(item){
        this.workbook.dataViews.push(item)
        this.isSelectViewDialogVisible = false
        this.currentUpdateDateTime = new Date()

        $('#dataviewstab').tab('show')
      },
      addFilter(item){
        this.workbook.filters.push(item)
        this.isSelectFilterDialogVisible = false
        this.currentUpdateDateTime = new Date()

        $('#presentationtab').tab('show')
      },
      addPresentation(item){
        this.workbook.presentations.push(item)
        this.idPresentationDialogVisible = false
        this.currentUpdateDateTime = new Date()

        $('#presentationtab').tab('show')
      },
      getColumnLabel(item){
        return item.name + " : " + item.type + "(" + item.size + ")"
      },
      showSelectSourceDialog(){
        this.isSelectSourceDialogVisible = true
        this.currentUpdateDateTime = new Date()

      },
      showSelectViewDialog(){
        this.isSelectViewDialogVisible = true
        this.currentUpdateDateTime = new Date()

      },
      showSelectFilterDialog(){
        this.isSelectFilterDialogVisible = true

        this.currentUpdateDateTime = new Date()

      },
      showSelectPresentationDialog(){
        this.idPresentationDialogVisible = true

        this.currentUpdateDateTime = new Date()

      },
      closeSelectSourceDialog(){
        this.isSelectSourceDialogVisible = false
        this.currentUpdateDateTime = new Date()

      },
      closeSelectViewDialog(){
        this.isSelectViewDialogVisible = false
        this.currentUpdateDateTime = new Date()

      },
      closeSelectFilterDialog(){
        this.isSelectFilterDialogVisible = false
        this.currentUpdateDateTime = new Date()

      },
      closeSelectPresentationDialog(){
        this.idPresentationDialogVisible = false
        this.currentUpdateDateTime = new Date()

      },
      removeDataSource(removeItem){
        this.workbook.dataSources = this.workbook.dataSources.filter(item => item.id != removeItem.id)

      },
      removeDataView(removeItem){
        this.workbook.dataViews = this.workbook.dataViews.filter(item => item.id != removeItem.id)

      },
      removeFilter(removeItem){
        this.workbook.filters = this.workbook.filters.filter(item => item.id != removeItem.id)

      },
      removePresentation(removeItem){
        this.workbook.presentations = this.workbook.presentations.filter(item => item.id != removeItem.id)

      },

    },
    created () {
        if("id" in this.$route.params){
            this.id = this.$route.params.id;

        }
        this.reloadData()

    }
};
</script>

