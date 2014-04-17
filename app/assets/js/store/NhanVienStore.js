/**
 * Created by dungvn3000 on 3/17/14.
 */
Ext.define('sunerp.store.NhanVienStore', {
    extend: 'sunerp.store.BaseStore',
    model: 'sunerp.model.NhanVien',
    proxy: {
        type: 'restx',
        url: '/nhanvien'
    },
    sorters: [
        {
            property: 'firstName'
        }
    ]
});