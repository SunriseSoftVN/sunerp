Ext.Loader.setConfig({
    enabled: true,
    paths: {
        'sunerp': '/assets/js'
    }
});

Ext.syncRequire(['Ext.Component', 'Ext.ComponentManager', 'Ext.ComponentQuery']);

