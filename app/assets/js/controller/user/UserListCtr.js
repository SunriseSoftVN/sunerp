Ext.define('sunerp.controller.user.UserListCtr', {
    extend: 'Deft.mvc.ViewController',
    inject: ['userStore'],
    config: {
        userStore: null
    },
    control: {
        view: {
            itemdblclick: "showEditView"
        },
        addBtn: {
            selector: 'button[action=addNew]',
            listeners: {
                click: 'showAddPanel'
            }
        }
    },
    init: function () {
        this.callParent(arguments);
    },
    showEditView: function (grid, record) {
        var view = Ext.create('sunerp.view.user.UserEdit', {
            user: record
        });
    },
    showAddPanel: function() {
        var view = Ext.create('sunerp.view.user.UserEdit');
    }
});