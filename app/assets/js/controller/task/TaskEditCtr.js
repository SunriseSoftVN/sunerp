/**
 * Created by dungvn3000 on 3/18/14.
 */

Ext.define('sunerp.controller.authority.TaskEditCtr', {
    extend: 'sunerp.controller.core.BaseEditController',
    inject: ['taskStore'],
    config: {
        taskStore: null
    },
    init: function() {
        this.mainStore = this.getTaskStore();
        this.callParent(arguments);
    }
});