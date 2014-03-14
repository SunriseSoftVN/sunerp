/**
 * Created by dungvn3000 on 3/14/14.
 * The model automatic update mapping field when set associations table.
 */

Ext.define('sunerp.model.BaseModel', {
    extend: 'Ext.data.Model',
    set: function (fieldName, newValue) {
        var me = this;
        Ext.each(me.associations.keys, function (key) {
            if (fieldName == key) {
                Ext.each(newValue.fields.keys, function (field) {
                    me.set(key + "." + field, newValue.get(field))
                });
                me.set(key + 'Id', newValue.get('id'));
            }
        });
        me.callParent(arguments);
    }
});