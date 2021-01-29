<template>
  <div class="card workbook-item">
      <div class="card-header">
          <span><b>{{getWorkbookDataSourceItem.connection.name}}</b></span>
          <br>
          <span>{{getWorkbookDataSourceItem.table}}</span>
          <button type="button" class="close" aria-label="Close" v-on:click="removeWorkbookItem">
              <span aria-hidden="true">&times;</span>
          </button>
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

</style>
<script>

export default {
    data () {
        return {

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

