/**
 * Created by dungvn3000 on 4/10/14.
 */

Ext.define('sunerp.component.filter.TextFilter', {
    extend: 'sunerp.component.core.BaseGirdFilter',
    fieldName: null,
    initComponent: function () {
        var me = this;
        if (me.comp) {
            var filter = new Ext.util.Filter({
                property: me.fieldName,
                value: null
            });
            me.filter = filter;
            me.store.addFilter(filter, false);
            me.comp.on('specialkey', me.onSpecialkey, me)
        }
        me.callParent(arguments);
    },
    onSpecialkey: function (f, e) {
        var me = this;
        var searchValue = f.getValue();
        if (e.getKey() == e.ENTER) {
            me.filter.setValue(searchValue);
            me.store.loadPage(1);
        }
    }
});