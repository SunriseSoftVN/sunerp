/**
 * Created by dungvn3000 on 4/9/14.
 */

Ext.define('sunerp.store.TrangThaiNhanVienStore', {
    extend: 'sunerp.store.BaseStore',
    model: 'sunerp.model.TrangThaiNhanVien',
    proxy: {
        type: 'restx',
        url: '/trangthainhanvien'
    }
});