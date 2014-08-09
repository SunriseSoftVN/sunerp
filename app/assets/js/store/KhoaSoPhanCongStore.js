/**
 * Created by Hiep on 14/03/14.
 */

Ext.define('sunerp.store.KhoaSoPhanCongStore', {
    extend: 'sunerp.store.BaseStore',
    model: 'sunerp.model.KhoaSoPhanCong',
    proxy: {
        type: 'restx',
        url: '/khoasophancong'
    },
    sorters: [
        {
            property: 'donVi.name'
        }
    ]
});