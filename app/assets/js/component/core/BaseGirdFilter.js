/**
 * Created by dungvn3000 on 4/10/14.
 */

Ext.define('sunerp.component.core.BaseGirdFilter', {
    extend: 'Ext.Component',
    comp: null,
    filter: null,
    store: null,
    constructor: function (config) {
        config = config || {};
        this.initConfig(config);
        this.callParent(arguments);
    },
    getFilter: function () {
        return this.filter;
    },
    getComponent: function () {
        return this.comp;
    }
});