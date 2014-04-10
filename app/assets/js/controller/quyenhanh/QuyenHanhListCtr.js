/**
 * Created by dungvn3000 on 3/21/14.
 */
Ext.define('sunerp.controller.quyenhanh.QuyenHanhListCtr', {
    extend: 'sunerp.controller.core.BaseListController',
    require: [
        'sunerp.component.ChucVuCb'
    ],
    inject: ['quyenHanhStore'],
    config: {
        quyenHanhStore: null,
        chucVuFilter: null
    },
    control: {
        chucVuCb: {
            selector: 'chucvucb',
            listeners: {
                change: 'onChange'
            }
        }
    },
    editView: 'sunerp.view.quyenhanh.QuyenHanhEdit',
    searchField: 'domain',
    init: function () {
        this.mainStore = this.getQuyenHanhStore();
        this.callParent(arguments);
    },
    afterInit: function () {
        this.mainStore.clearFilter(true);
        this.setChucVuFilter(new Ext.util.Filter({
            property: 'chucVuId',
            value: null
        }));
        this.mainStore.addFilter(this.getChucVuFilter(), true);
    },
    onChange: function (comp, newValue, oldValue, eOpts) {
        this.getChucVuFilter().setValue(sunerp.Utils.toString(newValue));
        this.mainStore.loadPage(1);
    }
});