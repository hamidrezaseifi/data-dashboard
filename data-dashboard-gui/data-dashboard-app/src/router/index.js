import Vue from 'vue'
import VueRouter from 'vue-router'
import Dashboard from '../views/auth/Dashboard.vue'
import About from '../views/auth/About.vue'
import LoginPage from '../views/noauth/LoginPage.vue'
import CustomerList from '../views/auth/customer/CustomerList.vue'
import EditCustomer from '../views/auth/customer/EditCustomer.vue'
import KrankenhausChart from '../views/auth/data/KrankenhausChart.vue'
import TestChart from '../views/auth/data/TestChart.vue'
import DataSourceSettings from '../views/auth/data/settings/DataSourceSettings.vue'
import DataSourceEdit from '../views/auth/data/settings/DataSourceEdit.vue'
import WorkbookEdit from '../views/auth/data/settings/WorkbookEdit.vue'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'Dashboard',
    component: Dashboard
  },
  {
    path: '/data/testchart',
    name: 'TestChart',
    component: TestChart
  },
  {
    path: '/data/krankenhaus',
    name: 'KrankenhausChart',
    component: KrankenhausChart
  },
  {
    path: '/data/settings/connections',
    name: 'DataSourceSettings',
    component: DataSourceSettings
  },
  {
    path: '/data/settings/connections/edit/:id',
    name: 'DataSourceEdit',
    component: DataSourceEdit,
    props: { clone: false }
  },
  {
    path: '/data/settings/connections/clone/:id',
    name: 'DataSourceEdit',
    component: DataSourceEdit,
    props: { clone: true }
  },
  {
    path: '/data/settings/connections/new',
    name: 'DataSourceEdit',
    component: DataSourceEdit
  },
   {
     path: '/data/settings/workbook/new',
     name: 'WorkbookEdit',
     component: WorkbookEdit
   },
 {
    path: '/about',
    name: 'About',
    component: About
  },
  {
   path: '/customerlist',
   name: 'CustomerList',
   component: CustomerList
  },
  {
   path: '/newcustomer',
   name: 'EditCustomer',
   component: EditCustomer
  },
  {
   path: '/customer/edit/:id',
   name: 'EditCustomer',
   component: EditCustomer
  },
  { path: '/login', component: LoginPage }
]

var router = new VueRouter({
  routes: routes
});


router.beforeEach((to, from, next) => {
  // redirect to login page if not logged in and trying to access a restricted page
  const publicPages = ['/login'];
  const authRequired = !publicPages.includes(to.path);
  const loggedIn = sessionStorage.getItem('login-data');

  if (authRequired && !loggedIn) {
    return next('/login');
  }

  next();
})

export default router