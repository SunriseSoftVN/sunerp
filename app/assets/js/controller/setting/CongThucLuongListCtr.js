/**
 * Created by dungvn3000 on 4/13/14.
 */

Ext.define('sunerp.controller.setting.CongThucLuongListCtr', {
    extend: 'sunerp.controller.core.BaseListController',
    inject: ['congThucLuongStore'],
    config: {
        congThucLuongStore: null
    },
    editView: 'sunerp.view.setting.CongThucLuongEdit',
    init: function () {
        this.getAddBtn().setDisabled(true);
        this.mainStore = this.getCongThucLuongStore();
        this.callParent(arguments);
    }
});