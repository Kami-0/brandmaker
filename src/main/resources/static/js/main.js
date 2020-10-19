var userApi = Vue.resource('/brandmaker-core/user{/id}');

Vue.component('user-form', {
    props: ['users'],
    data: function() {
      return {
          name: '',
          surname: '',
          email: '',
          yearOfBirth: ''
      }
    },
    template:
        '<div>' +
          '<input type="text" placeholder="name" v-model="name"/>' +
          '<input type="text" placeholder="surname" v-model="surname"/>' +
          '<input type="text" placeholder="email" v-model="email"/>' +
          '<input type="text" placeholder="year of birth" v-model="yearOfBirth"/>' +
          '<input type="button" value="save" @click="save"/>' +
        '</div>',
    methods: {
        save: function () {
            let user = {
                name: this.name,
                surname: this.surname,
                email: this.email,
                yearOfBirth: this.yearOfBirth
            }
            userApi.save({}, user).then(result =>
                result.json().then(user =>
                    this.users.push(user)
                )
            )
            this.name = ''
            this.surname = ''
            this.email = ''
            this.yearOfBirth = ''
        }
    }
});

Vue.component('users-table', {
    props: ['users', 'keys'],
    template:
        '<div>' +
          '<user-form :users="users" />' +
          '<table>' +
            '<thead>' +
              '<tr><th v-for="key in keys">{{ key }}</th></tr>' +
            '</thead>' +
            '<tbody' +
              '<tr v-for="us in users">' +
                '<td v-for="key in keys">{{ us[key] }}</td>' +
                '<input type="button" value="X" @click="del(us)"/>' +
              '</tr>' +
            '</tbody' +
          '</table>' +
        '</div>',
    created: function () {
        userApi.get().then(result =>
            result.json().then(data => {
                data.forEach(user => this.users.push(user))
                if (this.users.length !== 0) {
                    let userKeys = Object.keys(data[0])
                    userKeys.shift()
                    userKeys.forEach(key => this.keys.push(key))
                }
            })
        )
    },
    methods: {
        del: function (us) {
            console.log(us)
            userApi.remove({id: us['userId']}).then(result =>{
                if (result.ok) {
                    this.users.splice(this.users.indexOf(us), 1)
                }
            })
        }
    }
});

var app = new Vue({
    el: '#app',
    template: '<users-table :users="users" :keys="keys"/>',
    data: {
        users: [],
        keys: []
    }
})