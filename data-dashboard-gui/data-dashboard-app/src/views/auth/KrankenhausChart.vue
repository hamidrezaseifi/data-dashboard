
<template>
    <div class="Krankenhaus-view" style="">

        <div class="Krankenhaus-toolbar">
            <b>Krankenhaus</b>&nbsp; &nbsp;
            <select style="height: 30px;"  @change="onKrankenhausChange($event)">
                <option v-for="item in krankenhausList" v-bind:value="item.kh_key" v-bind:key="item.kh_key">{{item.krankenhaus_name}}</option>
            </select>
        </div>
        <div class="chart-container">
            <D3PieChart :config="fachabteilungConfig" :datum="fachabteilungList"></D3PieChart>
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
import { krankenhausService } from '../../services/krankenhaus.service';

export default {
  components: {
    D3PieChart,
    D3BarChart,
    D3LineChart,
    D3SlicesChart
  },
  data() {
    return {
      krankenhausList:[],
      fachabteilungList: [],
      erkrankungenList: [],
      behandlungList: [],
      selecteKhKy: '',
      fachabteilungConfig: {
        key: 'fachabteilung_name',
        value: 'fallzahl',
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
    onKrankenhausChange(event){
      console.log("current onKrankenhausChange", event.target.value)
      this.selecteKhKy = event.target.value
      this.reloadFachabteilungList(this.selecteKhKy)
      this.reloadErkrankungen(this.selecteKhKy)
      this.reloadBehandlungen(this.selecteKhKy)
    },
    reloadFachabteilungList(khKy){
        krankenhausService.getFachabteilung(khKy).then(async response => {
          const data = await response.json();
          if (!response.ok) {
            const error = (data && data.message) || response.statusText;
            return Promise.reject(error);
          }

          console.log("fachabteilungList", data);

          this.fachabteilungList = data




          }).catch(error => {
            console.error("There was an error!", error);
      })
    },
    reloadErkrankungen(khKy){
       console.log("reloadErkrankungen", khKy);
    },
    reloadBehandlungen(khKy){
        console.log("reloadBehandlungen", khKy);
    }
  },
  created () {

      krankenhausService.getAll().then(async response => {
          const data = await response.json();
          if (!response.ok) {
            const error = (data && data.message) || response.statusText;
            return Promise.reject(error);
          }

          this.krankenhausList = [{"krankenhaus_name" : "" , "kh_key" : "krankenhaus auswählen"}]

          this.krankenhausList = [{"krankenhaus_name" : "" , "kh_key" : ""}].concat(data);

          }).catch(error => {
            console.error("There was an error!", error);
      })
  }
}
</script>

<style>

.Krankenhaus-view{
    margin: 40px 40px;
}

.Krankenhaus-toolbar{
    margin: 10px 0;
    background: #efefef;
    padding: 10px 10px;
    border: 1px solid gray;
    border-radius: 5px;
}

.chart-container {
    float:left;
    width: 550px;
    height: 400px;
    border: 1px solid gray;
    padding: 5px;
    margin: 5px;
}
</style>
