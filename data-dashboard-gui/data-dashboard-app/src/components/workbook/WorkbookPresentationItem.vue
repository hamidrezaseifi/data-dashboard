<template>
  <div class="card presentation-item">
      <div class="card-header">
          <div>
              <img style="float: left;" src="@/assets/images/presentation.png" width="20" />
              <span style="display: inline-block; float: left; margin-left: 5px;"><b>{{getPresentationItem.name}}</b></span>
              <button type="button" class="close" aria-label="Close" style="float: right; margin-top: -3px;" v-on:click="removePresentation(item)" title="Löschen">
                  <span aria-hidden="true">&times;</span>
              </button>
              <span style="display: inline-block; clear: both;"></span>
          </div>

      </div>
      <div class="card-body" style="padding: 0;">
          <div class="presentation-form-group">
              <label class="presentation-item-title">Datenansicht</label> <span>{{getPresentationItem.dataView.name}}</span>
          </div>
          <div class="presentation-form-group">
              <label class="presentation-item-title">Präsentationstyp</label> <span>{{getPresentationItem.presentationType}}</span>
          </div>
          <div class="presentation-form-group">
              <label class="presentation-item-title">Präsentationsstil</label> <span>{{getPresentationItem.presentationStyle}}</span>
          </div>
          <div class="filter-form-group">
              <label class="filter-item-title">Raum</label> <span>Spalte: {{getPresentationItem.columnIndex + 1}} Reihe:{{getPresentationItem.rowIndex + 1}}</span>
          </div>
          <div class="presentation-form-group">
              <div v-for="item in getPresentationItem.properties" :key="item.name" class="presentation-property">
                  <label class="presentation-item-title">{{item.name}}</label>
                  <span class="presentation-value">{{item.value}}</span>
                  <div class="clear"></div>
              </div>

          </div>

      </div>

  </div>

</template>

<style>

.presentation-item {
    width: 350px;
    max-height: 350px;
    height: 350px;
    float: left;
    margin: 5px;
    font-size: 12px;
}

.presentation-item .column-item {
   padding: 4px;

   overflow: hidden;
}

.presentation-item .column-item span {
    display: inline-block;
    width: 220px;
    max-width: 220px;
    max-height: 16px;
    overflow: hidden;
}

.presentation-item .card-header{
   padding: 8px 8px;
}

.presentation-item .card-body {
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

.presentation-item-title{
    width: 90px;
}

.presentation-form-group{
    margin: 8px;
}

.presentation-value{
    max-width: calc(100% - 95px);
    overflow: hidden;
    display: inline-block;
    height: 18px;
}

.presentation-property span, .presentation-property label{
    float: left;
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
    props:["presentation"],
    computed: {
      getPresentationItem: function (){
        this.currentUpdateDateTime
        return this.presentation
      }
    },
    methods: {

      getColumnLabel(item){
        return item.columnName + " : " + item.columnType
      },
      removePresentation(){

            this.$emit('removePresentation', this.presentation)
      }

    },
    created () {


    }
};
</script>

