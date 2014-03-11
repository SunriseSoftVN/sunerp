Ext.define('sunerp.controller.user.UserEditCtr', {
    extend: 'sunerp.controller.core.BaseEditController',
    inject: ['userStore'],
    config: {
        userStore: null
    },
    init: function() {
        this.mainStore = this.getUserStore();
        this.callParent(arguments);
    }
});