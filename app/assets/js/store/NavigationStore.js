/**
 * Created by dungvn3000 on 2/19/14.
 */

Ext.define('sunerp.store.NavigationStore', {
    extend: 'Ext.data.TreeStore',
    model: 'sunerp.model.MenuItem',
    proxy: {
        type: 'ajax',
        url: '/menuItems',
        reader: {
            type: 'json'
        }
    }
});