<template>
  <div class="card workbook-item">
      <div class="card-header">
          <div>
              <span style="display: inline-block; float: left;"><b>{{getWorkbookDataSourceItem.name}}</b></span>
              <button type="button" class="close" aria-label="Close" style="margin-left: 5px;" v-on:click="removeWorkbookItem" title="Löschen">
                  <span aria-hidden="true">&times;</span>
              </button>
              <img src="@/assets/images/info.png" class="datasource-icon info-button" v-on:click="showDetail =!showDetail" title="Information" />
              <span style="display: inline-block; clear: both;"></span>
          </div>
          <div style=" margin-top: 10px;">
            <div v-if="showDetail">
                <div class="connectiondetail" title="Verbindung">
                    <img src="@/assets/images/database.png" class="datasource-icon"/>
                    <b>{{getWorkbookDataSourceItem.connectionName}}</b>
                </div>
                <div v-if="getWorkbookDataSourceItem.sourceType=='TABLE'" class="connectiondetail" title="Tabelle">
                    <img src="@/assets/images/table.png" class="datasource-icon" />
                    {{getWorkbookDataSourceItem.table}}
                </div>
                <div v-if="getWorkbookDataSourceItem.sourceType=='QUERY'" class="sqldetail" title="SQL Skript">
                    <img src="@/assets/images/sql.png" class="datasource-icon" />
                    {{getWorkbookDataSourceItem.query}}
                </div>

            </div>
          </div>
      </div>
      <div class="card-body">
          <ul  class="list-group">
              <li class="list-group-item" v-for="column in getWorkbookDataSourceItem.columns" :key="column.name" >

                  <span style="margin-left: 9px;">{{ getColumnLabel(column)}}</span>
              </li>
          </ul >

      </div>
  </div>

</template>

<style>

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
    props:["workbookDataSourceItem"],
    computed: {
      getWorkbookDataSourceItem: function (){
        this.currentUpdateDateTime
        return this.workbookDataSourceItem
      }
    },
    methods: {

      getColumnLabel(item){
        return item.name + " : " + item.type + "(" + item.size + ")"
      },
      removeWorkbookItem(){

            this.$emit('removeWorkbookItem', this.workbookDataSourceItem)
      }

    },
    created () {


    }
};
</script>

