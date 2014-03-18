/**
 * Created by dungvn3000 on 3/18/14.
 */

Ext.define('sunerp.controller.authority.TaskSListCtr', {
    extend: 'sunerp.controller.core.BaseListController',
    inject: ['taskStore'],
    config: {
        taskStore: null
    },
    editView: 'sunerp.view.authority.TaskSEdit',
    searchField: 'name',
    init: function () {
        this.mainStore = this.getTaskStore();
        this.callParent(arguments);
    }
});