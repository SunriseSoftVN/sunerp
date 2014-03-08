'use strict';
/**
 * Created by dungvn3000 on 3/8/14.
 */

Ext.define('sunerp.Application', {
    extend: 'Deft.mvc.Application',
    requires: ['Ext.tip.QuickTipManager', 'Ext.window.MessageBox'],

    /**
     * init() runs when Ext.onReady() is called.
     */
    init: function () {
        //hide loading
        Ext.select("#loading-container").hide();
        Ext.tip.QuickTipManager.init();
        return Ext.create("sunerp.view.Viewport");
    }
});