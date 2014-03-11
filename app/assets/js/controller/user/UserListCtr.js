Ext.define('sunerp.controller.user.UserListCtr', {
    extend: 'sunerp.controller.core.BaseListController',
    inject: ['userStore'],
    config: {
        userStore: null
    },
    editView: 'sunerp.view.user.UserEdit',
    init: function() {
        this.mainStore = this.getUserStore();
        this.callParent(arguments);
    }
});