/**
 * Created by dungvn3000 on 3/12/14.
 */

Ext.define('sunerp.store.TaskStore', {
    extend: 'sunerp.store.BaseStore',
    model: 'sunerp.model.Task',
    proxy: {
        type: 'restx',
        url: '/task'
    }
});