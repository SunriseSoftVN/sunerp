'use strict';
/**
 * Created by dungvn3000 on 3/8/14.
 */

Ext.define('sunerp.Application', {
    extend: 'Deft.mvc.Application',
    requires: [
        'Ext.tip.QuickTipManager',
        'Ext.window.MessageBox',
        'sunerp.Utils',
        'sunerp.store.CacKhoanCongStore',
        'sunerp.store.CacKhoangTruStore',
        'sunerp.store.ChucVuStore',
        'sunerp.store.CompanySettingStore',
        'sunerp.store.CompanyStore',
        'sunerp.store.DonViStore',
        'sunerp.store.NavigationStore',
        'sunerp.store.NhanVienStore',
        'sunerp.store.PhongBanStore',
        'sunerp.store.QuyenHanhStore',
        'sunerp.store.QuyLuongStore',
        'sunerp.store.SoLuongStore',
        'sunerp.store.SoPhanCongExtStore',
        'sunerp.store.SoPhanCongStore',
        'sunerp.store.TaskStore',
        'sunerp.store.XepLoaiStore',
        'sunerp.service.UserService'
    ],

    /**
     * init() runs when Ext.onReady() is called.
     */
    init: function () {
        //hide loading
        Deft.Injector.configure(this.buildInjectorConfiguration());
        Deft.promise.Deferred.enableLogging = false;
        Ext.select("#loading-container").hide();
        Ext.tip.QuickTipManager.init();
        Ext.Date.monthNames = [
            "T1",
            "T2",
            "T3",
            "T4",
            "T5",
            "T6",
            "T7",
            "T8",
            "T9",
            "T10",
            "T11",
            "T12"
        ];

        Ext.Ajax.on('beforerequest', this.beforeAjaxRequest);
        Ext.Ajax.on('requestcomplete', this.requestAjaxComplete);
        Ext.Ajax.on('requestexception', this.requestAjaxException);

        return Ext.create("sunerp.view.Viewport");
    },
    beforeAjaxRequest: function (conn, options) {
        Ext.select('#waiting-container').show();
    },
    requestAjaxComplete: function (conn, response, options) {
        Ext.select('#waiting-container').hide();
    },
    requestAjaxException: function (conn, response, options) {
        Ext.select('#waiting-container').hide();
        Ext.Msg.alert('Error ' + response.status, response.responseText);
    },
    buildInjectorConfiguration: function () {
        var config;
        config = {
            cacKhoanCongStore:      'sunerp.store.CacKhoanCongStore',
            cacKhoangTruStore:      'sunerp.store.CacKhoangTruStore',
            chucVuStore:            'sunerp.store.ChucVuStore',
            companySettingStore:    'sunerp.store.CompanySettingStore',
            companyStore:           'sunerp.store.CompanyStore',
            donViStore:             'sunerp.store.DonViStore',
            navigationStore:        'sunerp.store.NavigationStore',
            nhanVienStore:          'sunerp.store.NhanVienStore',
            phongBanStore:          'sunerp.store.PhongBanStore',
            quyenHanhStore:         'sunerp.store.QuyenHanhStore',
            quyLuongStore:          'sunerp.store.QuyLuongStore',
            soLuongStore:           'sunerp.store.SoLuongStore',
            soPhanCongExtStore:     'sunerp.store.SoPhanCongExtStore',
            soPhanCongStore:        'sunerp.store.SoPhanCongStore',
            taskStore:              'sunerp.store.TaskStore',
            xepLoaiStore:           'sunerp.store.XepLoaiStore',
            userService:            'sunerp.service.UserService'
        };
        return config;
    }
});