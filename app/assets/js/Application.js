'use strict';
/**
 * Created by dungvn3000 on 3/8/14.
 */

Ext.define('sunerp.Application', {
    extend: 'Deft.mvc.Application',
    requires: [
        'Ext.tip.QuickTipManager',
        'Ext.window.MessageBox',
        'sunerp.store.AuthorityStore',
        'sunerp.store.CacKhoanCongStore',
        'sunerp.store.CacKhoangTruStore',
        'sunerp.store.ChucVuStore',
        'sunerp.store.CompanySettingStore',
        'sunerp.store.CompanyStore',
        'sunerp.store.DonViStore',
        'sunerp.store.KhoiDonViStore',
        'sunerp.store.NavigationStore',
        'sunerp.store.NhanVienStore',
        'sunerp.store.PhongBangStore',
        'sunerp.store.QuyLuongStore',
        'sunerp.store.RoleStore',
        'sunerp.store.SoLuongStore',
        'sunerp.store.SoPhanCongExtStore',
        'sunerp.store.SoPhanCongStore',
        'sunerp.store.TaskStore',
        'sunerp.store.UserStore'
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
        return Ext.create("sunerp.view.Viewport");
    },

    buildInjectorConfiguration: function() {
        var config;
        config = {
            authorityStore:         'sunerp.store.AuthorityStore',
            cacKhoanCongStore:      'sunerp.store.CacKhoanCongStore',
            cacKhoangTruStore:      'sunerp.store.CacKhoangTruStore',
            chucVuStore:            'sunerp.store.ChucVuStore',
            companySettingStore:    'sunerp.store.CompanySettingStore',
            companyStore:           'sunerp.store.CompanyStore',
            donViStore:             'sunerp.store.DonViStore',
            khoiDonViStore:         'sunerp.store.KhoiDonViStore',
            navigationStore:        'sunerp.store.NavigationStore',
            nhanVienStore:          'sunerp.store.NhanVienStore',
            phongBangStore:         'sunerp.store.PhongBangStore',
            quyLuongStore:          'sunerp.store.QuyLuongStore',
            roleStore:              'sunerp.store.RoleStore',
            SoLuongStore:           'sunerp.store.SoLuongStore',
            soPhanCongExtStore:     'sunerp.store.SoPhanCongExtStore',
            soPhanCongStore:        'sunerp.store.SoPhanCongStore',
            taskStore:              'sunerp.store.TaskStore',
            userStore:              'sunerp.store.UserStore'
        };
        return config;
    }
});