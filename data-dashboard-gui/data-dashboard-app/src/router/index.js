import Vue from 'vue'
import VueRouter from 'vue-router'
import Dashboard from '../views/auth/Dashboard.vue'
import About from '../views/auth/About.vue'
import TestChart from '../views/auth/TestChart.vue'
import LoginPage from '../views/noauth/LoginPage.vue'
import CustomerList from '../views/auth/customer/CustomerList.vue'
import EditCustomer from '../views/auth/customer/EditCustomer.vue'
import KrankenhausChart from '../views/auth/KrankenhausChart.vue'


Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'Dashboard',
    component: Dashboard
  },
  {
    path: '/test',
    name: 'TestChart',
    component: TestChart
  },
  {
    path: '/krankenhaus',
    name: 'KrankenhausChart',
    component: KrankenhausChart
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