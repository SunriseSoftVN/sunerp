/**
 * Created by dungvn3000 on 3/11/14.
 */

Ext.define('sunerp.controller.core.BaseEditController', {
    extend: 'Deft.mvc.ViewController',
    //this property has to be set in subclass
    mainStore: null,
    reloadAfterInsert: false,
    control: {},
    constructor: function (config) {
        this.control['form'] = {
            selector: 'form'
        };
        this.control['saveBtn'] = {
            selector: 'button[action=save]',
            listeners: {
                click: 'doUpdate'
            }
        };
        this.callParent(config);
    },
    init: function () {
        if (this.getView().getModel() != null) {
            this.getForm().loadRecord(this.getView().getModel());
        }
        this.callParent(arguments);
        this.afterInit();
    },
    afterInit: function () {
    },
    doUpdate: function () {
        var view = this.getView(),
            form = this.getForm(),
            record = form.getRecord(),
            values = form.getValues(),
            me = this;
        if (form.isValid()) {
            if (record == null) {
                record = Ext.create(this.mainStore.model);
                this.mainStore.add(record);
            }

            record.set(values);

            form.getForm().getFields().each(function (field) {
                if (field.getXType() == "comboboxx" || field.superclass.xtype == "comboboxx") {
                    var data = field.getSelectedData();
                    if (data) {
                        record.set(field.getModelName() + "Id", data.id);
                        record.set(field.getModelName() + "." + field.displayField, data[field.displayField]);
                    }
                }
                if (field.superclass.self.getName() == "sunerp.component.core.BasePicker") {
                    var data = field.getSelect();
                    if(data) {
                        record.set(field.getModelName() + "Id", data.id);
                    }
                    me.reloadAfterInsert = true;
                }
            });

            if (record == null || record.dirty) {
                // synchronize the store after editing the record
                me.mainStore.sync({
                    success: function () {
                        if(me.reloadAfterInsert) {
                            me.mainStore.reload();
                        }
                        view.close();
                    },
                    failure: function () {
                        if (record.get('id') == null) {
                            me.mainStore.remove(record);
                        }
                    }
                });
            } else {
                view.close();
            }
        }
    }
});