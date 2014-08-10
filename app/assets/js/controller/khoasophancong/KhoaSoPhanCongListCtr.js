/**
 * Created by dungvn3000 on 3/18/14.
 */

Ext.define('sunerp.controller.khoasophancong.KhoaSoPhanCongListCtr', {
    extend: 'sunerp.controller.core.BaseListController',
    inject: ['khoaSoPhanCongStore'],
    config: {
        khoaSoPhanCongStore: null
    },
    control: {
        btnSave: {
            selector: 'button[action=save]',
            listeners: {
                click: 'onBtnSaveClick'
            }
        }
    },
    init: function () {
        this.mainStore = this.getKhoaSoPhanCongStore();
        this.getAddBtn().setDisabled(true);
        this.callParent(arguments);
    },
    onBtnSaveClick: function () {
        this.mainStore.sync({
            success: function () {
                Ext.Msg.alert('Status', 'Cập nhật thành công.');
            }
        });
    }
});