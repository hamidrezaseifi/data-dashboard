<template>
  <div class="card workbook-item">
      <div class="card-header">
          <div>
              <span style="display: inline-block; float: left;"><b>{{getWorkbookDataViewItem.name}}</b></span>
              <button type="button" class="close" aria-label="Close" style="float: right; margin-top: -3px;" v-on:click="removeDataView(item)" title="Löschen">
                  <span aria-hidden="true">&times;</span>
              </button>
              <span style="display: inline-block; clear: both;"></span>
          </div>

      </div>
      <div class="card-body" style="padding: 0;">
          <ul  class="list-group">
              <li class="list-group-item dataview-column-item" v-for="item in getWorkbookDataViewItem.columns" :key="item.id" v-bind:value="item.id">
                  <div style="display: block" class="">{{item.label}} ({{item.sourceColumn.columnName}})</div>
                  <label style="margin-right: 4px;" v-bind:for="'chkuseSelect' + item.sourceColumn.id">Als Egebnis</label>
                  <input v-bind:id="'chkuseSelect' + item.sourceColumn.id" style="margin-right: 9px;" disabled type="checkbox" v-model="item.useAsSelect">
                  <label style="margin-right: 4px;">Egebnis Typ: {{item.selectType}}</label>

              </li>
          </ul >

      </div>

  </div>

</template>

<style>

.workbook-item {
    width: 260px;
    max-height: 400px;
    height: 400px;
    float: left;
    margin: 5px;
    font-size: 12px;
}

.workbook-item .column-item {
   padding: 4px;

   overflow: hidden;
}

.workbook-item .column-item span {
    display: inline-block;
    width: 220px;
    max-width: 220px;
    max-height: 16px;
    overflow: hidden;
}

.workbook-item .card-header{
   padding: 8px 8px;
}

.workbook-item .card-body {
    height: calc(100% - 40px);
    max-height: calc(100% - 40px);
    overflow: auto;
    padding: 0;
}

img.datasource-icon{
    width: 18px;
    margin-right: 5px;
}

img.info-button{
    cursor: pointer;
    margin: 0 5px;
    float: right;
}

div.sqldetail{
    border: 1px solid gray;
    padding: 5px;
}

div.connectiondetail{
    border: 1px solid gray;
    padding: 5px;
    margin-bottom: 5px;
}

.list-group-item.dataview-column-item{
    padding: 6px;
}
</style>
<script>

export default {
    data () {
        return {
            showDetail: false
        }
    },
    components:{

    },
    props:["workbookDataView"],
    computed: {
      getWorkbookDataViewItem: function (){
        this.currentUpdateDateTime
        return this.workbookDataView
      }
    },
    methods: {

      getColumnLabel(item){
        return item.columnName + " : " + item.columnType
      },
      removeDataView(){

            this.$emit('removeDataView', this.workbookDataView)
      }

    },
    created () {


    }
};
</script>

