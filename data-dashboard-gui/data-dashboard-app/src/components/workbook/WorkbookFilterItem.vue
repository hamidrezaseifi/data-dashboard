<template>
  <div class="card filter-item">
      <div class="card-header">
          <div>
              <img style="float: left;" src="@/assets/images/filter.png" width="20" />
              <span style="display: inline-block; float: left; margin-left: 5px;"><b>{{getWorkbookFilterItem.name}}</b></span>
              <button type="button" class="close" aria-label="Close" style="float: right; margin-top: -3px;" v-on:click="removeFilter(item)" title="Löschen">
                  <span aria-hidden="true">&times;</span>
              </button>
              <span style="display: inline-block; clear: both;"></span>
          </div>

      </div>
      <div class="card-body" style="padding: 0;">
          <div class="filter-form-group">
              <label class="filter-item-title">Datenansicht</label> <span>{{getWorkbookFilterItem.dataView.name}}</span>
          </div>
          <div class="filter-form-group">
              <label class="filter-item-title">Filter-Sicht-Typ</label> <span>{{getWorkbookFilterItem.filterType}}</span>
          </div>
          <div class="filter-form-group">
              <label class="filter-item-title">Filter-Schlüssel</label> <span>{{getWorkbookFilterItem.filterKey}}</span>
          </div>
          <div class="filter-form-group">
              <label class="filter-item-title">Raum</label> <span>Spalte: {{getWorkbookFilterItem.columnIndex + 1}} Reihe:{{getWorkbookFilterItem.rowIndex + 1}}</span>
          </div>

      </div>

  </div>

</template>

<style>

.filter-item {
    width: 260px;
    max-height: 200px;
    height: 200px;
    float: left;
    margin: 5px;
    font-size: 12px;
}

.filter-item .column-item {
   padding: 4px;

   overflow: hidden;
}

.filter-item .column-item span {
    display: inline-block;
    width: 220px;
    max-width: 220px;
    max-height: 16px;
    overflow: hidden;
}

.filter-item .card-header{
   padding: 8px 8px;
}

.filter-item .card-body {
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

.filter-item-title{
    width: 90px;
}

.filter-form-group{
    margin: 8px;
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
    props:["workbookFilter"],
    computed: {
      getWorkbookFilterItem: function (){
        this.currentUpdateDateTime
        return this.workbookFilter
      }
    },
    methods: {

      getColumnLabel(item){
        return item.columnName + " : " + item.columnType
      },
      removeFilter(){

            this.$emit('removeFilter', this.workbookFilter)
      }

    },
    created () {


    }
};
</script>

