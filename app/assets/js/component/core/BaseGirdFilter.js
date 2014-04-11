/**
 * Created by dungvn3000 on 4/10/14.
 */

Ext.define('sunerp.component.core.BaseGirdFilter', {
    extend: 'Ext.Component',
    comp: null,
    filter: null,
    store: null,
    fieldName: null,
    constructor: function (config) {
        config = config || {};
        this.initConfig(config);
        this.callParent(arguments);
    },
    initComponent: function () {
        var me = this;
        var filter = new Ext.util.Filter({
            property: me.fieldName,
            value: sunerp.Utils.toString(me.comp.getValue())
        });
        me.filter = filter;
        me.store.addFilter(filter, false);
        me.callParent(arguments);
    },
    getFilter: function () {
        return this.filter;
    },
    getComponent: function () {
        return this.comp;
    }
});