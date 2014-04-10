/**
 * Created by dungvn3000 on 4/10/14.
 */

Ext.define('sunerp.component.filter.TextFilter', {
    extend: 'sunerp.component.core.BaseGirdFilter',
    fieldName: null,
    constructor: function () {
        var me = this;
        if (me.comp) {
            var value = me.comp.getValue();
            var filter = new Ext.util.Filter({
                property: me.fieldName,
                value: value
            });
            me.filter = filter;
        }
        me.callParent(arguments);
    }
});