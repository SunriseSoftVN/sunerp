/**
 * Created by dungvn3000 on 4/10/14.
 */

Ext.define('sunerp.component.core.BaseGirdFilter', {
    comp: null,
    filter: null,
    constructor: function () {
        this.callParent(arguments);
    },
    getFilter: function () {
        return this.filter;
    },
    getComponent: function () {
        return this.comp;
    }
});