'use strict';
/**
 * Created by dungvn3000 on 3/8/14.
 */

Ext.define('sunerp.Application', {
    extend: 'Deft.mvc.Application',
    requires: [
        'Ext.tip.QuickTipManager',
        'Ext.window.MessageBox',
        'sunerp.store.UserStore',
        'sunerp.store.NavigationStore',
        'sunerp.store.RoleStore',
        'sunerp.store.AuthorityStore',
        'sunerp.store.SoPhanCongStore',
        'sunerp.store.TaskStore',
        'sunerp.store.ChucVuStore'
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
            userStore: 'sunerp.store.UserStore',
            roleStore: 'sunerp.store.RoleStore',
            navigationStore: 'sunerp.store.NavigationStore',
            authorityStore: 'sunerp.store.AuthorityStore',
            soPhanCongStore: 'sunerp.store.SoPhanCongStore',
            taskStore: 'sunerp.store.TaskStore',
            chucVuStore: 'sunerp.store.ChucVuStore'
        };
        return config;
    }
});