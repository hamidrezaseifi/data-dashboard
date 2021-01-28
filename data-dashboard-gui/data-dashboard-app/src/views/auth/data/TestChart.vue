
<template>
    <div class="my-app" style="">

        <!-- chart -->
        <div class="chart-container">
            <D3PieChart :config="chart_config" :datum="chart_data"></D3PieChart>
        </div>
        <div class="chart-container">
            <D3BarChart
                        :config="chart_config_ver"
                        :datum="chart_data_bar"
                        :title="chart_title_bar"
                        :source="chart_source_bar" ></D3BarChart>

        </div>

        <div class="chart-container">
            <D3BarChart
                    :config="chart_config_bar_hor"
                    :datum="chart_data_bar"
                    :title="chart_title_bar"
                    :source="chart_source_bar" ></D3BarChart>

        </div>
        <div class="chart-container">
            <D3LineChart :config="chart_config_line1" :datum="chart_data_line1"></D3LineChart>

        </div>
        <div class="chart-container">
            <D3SlicesChart :config="chart_config_slice1" :datum="chart_data_slice1"></D3SlicesChart>

        </div>

        <div style="clear: both; height: 50px;"></div>



        <button style="clear:both;" @click="addData()">Add</button>
        <button @click="removeFirst()">Remove first</button>
        <button @click="removeLast()">Remove last</button>

    </div>
</template>

<script>
import { D3PieChart } from 'vue-d3-charts';
import { D3BarChart } from 'vue-d3-charts';
import { D3LineChart } from 'vue-d3-charts';
import { D3SlicesChart } from 'vue-d3-charts';

export default {
  components: {
    D3PieChart,
    D3BarChart,
    D3LineChart,
    D3SlicesChart
  },
  data() {
    return {
      chart_data: [
        {hours: 20, name: 'Lorem'},
        {hours: 30, name: 'Ipsum'},
        {hours: 31, name: 'Dolor'},
        {hours: 15, name: 'Sit'},
      ],
      chart_config: {
        key: 'name',
        value: 'hours',
        color: {scheme: 'schemeTableau10'},
        radius: {inner: 80}
      },
      count: 1,
      //width: 500,
      chart_title_bar: 'Your title goes here',
      chart_source_bar: 'Your source goes here',
      chart_data_bar: [
        {lorem: 1648, ipsum: 9613, dolor: 1390, sit: 9130, year: '2007'},
        {lorem: 2479, ipsum: 6315, dolor: 1560, sit: 6150, year: '2008'},
        {lorem: 3200, ipsum: 12541, dolor: 5421, sit: 2541, year: '2009'},
      ],
      chart_config_ver: {
        key: 'year',
        orientation: "vertical",
        //currentKey: '2007',
        values: ['lorem', 'ipsum', 'dolor', 'sit'],
        axis: {
          yTicks: 4
        },
        color: {
          keys: {
            lorem: '#F98B3B',
            ipsum: '#F26643',
            dolor: '#FF463D',
            sit: '#C92C39',
          },
        }
      },
      chart_config_bar_hor: {
        key: 'year',
        orientation: "horizontal",
        //currentKey: '2007',
        values: ['lorem', 'ipsum', 'dolor', 'sit'],
        axis: {
          yTicks: 4
        },
        color: {
          keys: {
            lorem: '#F98B3B',
            ipsum: '#F26643',
            dolor: '#FF463D',
            sit: '#C92C39',
          },
        }
      },
      chart_data_line1: [
        {hours: 238, production: 134, date: 2000},
        {hours: 938, production: 478, date: 2001},
        {hours: 1832, production: 1392, date: 2002},
        {hours: 2092, production: 2343, date: 2003},
        {hours: 2847, production: 2346, date: 2004},
        {hours: 2576, production: 2233, date: 2005},
        {hours: 2524, production: 2325, date: 2006},
        {hours: 1648, production: 2456, date: 2007},
        {hours: 2479, production: 2329, date: 2008},
        {hours: 3200, production: 2438, date: 2009}
      ],
      chart_config_line1: {
        values: ['hours', 'production'],
        date: {
          key: 'date',
          inputFormat: '%Y',
          outputFormat: '%Y',
        },
        points:true,
        axis: {
          yTicks: 3,
        }
      },
      chart_data_slice1: [
        {hours: 40, name: 'Lorem'},
        {hours: 60, name: 'Ipsum'},
        {hours: 31, name: 'Dolor'},
        {hours: 15, name: 'Sit'},
       ],
       chart_config_slice1: {
          key: 'name',
          value: 'hours',
          color: {scheme: 'schemeTableau10'},
          radius: {inner: 60}


        },
    }
  },
  methods: {
    addData(){
      const hours = Math.floor(Math.random() * 30);
      const name = 'New '+this.count++;
      this.chart_data.push({hours, name})
    },
    removeFirst(){
      if(!this.chart_data.length) return;
      this.chart_data.splice(0, 1);
    },
    removeLast(){
      if(!this.chart_data.length) return;
      this.chart_data.splice(this.chart_data.length-1, 1);
    }
  }
}
</script>

<style>
  .chart-container {
    float:left;
    width: 550px;
    height: 400px;
    border: 1px solid gray;
    padding: 5px;
    margin: 5px;
  }
</style>
