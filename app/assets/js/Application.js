'use strict';
/**
 * Created by dungvn3000 on 3/8/14.
 */

Ext.define('sunerp.Application', {
    extend: 'Deft.mvc.Application',
    requires: [
        'Ext.tip.QuickTipManager',
        'Ext.window.MessageBox',
        'sunerp.store.Users',
        'sunerp.store.Roles',
        'sunerp.store.Authorities'
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
            users: 'sunerp.store.Users',
            roles: 'sunerp.store.Roles',
            authorities: 'sunerp.store.Authorities'
        };
        return config;
    }
});