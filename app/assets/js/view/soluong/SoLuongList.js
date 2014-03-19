/**
 * Created by dungvn3000 on 3/18/14.
 */
Ext.define('sunerp.view.soluong.SoLuongList', {
    extend: 'sunerp.view.core.BaseListView',
    requires: [
        'sunerp.controller.soluong.SoLuongListCtr',
        'sunerp.component.NhanVienPicker'
    ],
    tbar: [
        {
            xtype: 'textfield',
            name: 'searchField',
            hideLabel: true,
            width: 200
        },
        {
            text: 'Thêm mới',
            tooltip: 'Thêm mới',
            iconCls: 'add',
            action: 'addNew'
        },
        {
            text: 'Cập nhật',
            tooltip: 'Cập nhật',
            iconCls: 'save',
            action: 'save'
        }
    ],
    alias: 'widget.soLuongList',
    controller: 'sunerp.controller.soluong.SoLuongListCtr',
    inject: ['soLuongStore'],
    config: {
        soLuongStore: null
    },
    initComponent: function () {
        var me = this;
        me.store = this.getSoLuongStore();
        me.columns = [
            {xtype: 'rownumberer'},
            {
                header: 'Họ tên',
                dataIndex: 'nhanVien.firstName',
                width: 160,
                editor: {
                    xtype: 'nhanvienpicker',
                    gird: me
                }
            },
            {header: 'Chức vụ', dataIndex: 'chucVu', width: 100},
            {header: 'Hệ số lương', dataIndex: 'heSoLuong', width: 100},
            {header: 'Luong ND', dataIndex: 'luongNd', width: 100},
            {header: 'K2', dataIndex: 'k2', width: 50},
            {header: 'Lương SP', dataIndex: 'luongSP', width: 100},
            {
                text: 'Lương thời gian',
                columns: [
                    {
                        text     : 'Cộng',
                        dataIndex: 'luongTgCong'
                    },
                    {
                        text     : 'Tiền',
                        dataIndex: 'luongTgTien'
                    }
                ]
            },
            {header: 'Phụ cấp TN', dataIndex: 'cacKhoangCong.phuCapTn', width: 100},
            {header: 'Phụ cấp LD', dataIndex: 'cacKhoangCong.phuCapLd', width: 100},
            {header: 'Trực BHLD', dataIndex: 'cacKhoangCong.trucBHLD', width: 100},
            {header: 'Phụ cấp KV', dataIndex: 'cacKhoangCong.phuCapKV', width: 100},
            {header: 'Cộng phần lương', dataIndex: 'cacKhoangCong.congPhanLuong', width: 130},
            {header: 'Chi khác', dataIndex: 'cacKhoangCong.chiKhac', width: 90},
            {header: 'Lương độc hại', dataIndex: 'cacKhoangCong.luongDocHai', width: 125},
            {header: 'Nước uống', dataIndex: 'cacKhoangCong.nuocUong', width: 100},
            {header: 'Ăn giữa ca', dataIndex: 'cacKhoangCong.anGiuaCa', width: 100},
            {header: 'Ốm đau sinh đẻ', dataIndex: 'cacKhoangCong.omDauSinhDe', width: 130},
            {
                text: 'Các khoản trừ',
                columns: [
                    {
                        text     : 'BHYT',
                        dataIndex: 'cacKhoangTru.bhyt'
                    },
                    {
                        text     : 'BHXH',
                        dataIndex: 'cacKhoangTru.bhxh'
                    }
                ]
            },
            {header: 'Đoàn phỉ', dataIndex: 'cacKhoangTru.doanPhi', width: 100},
            {header: 'Ứng kỳ 1', dataIndex: 'cacKhoangTru.ungKy1', width: 100},
            {header: 'Thu nợ', dataIndex: 'cacKhoangTru.thuNo', width: 100},
            {header: 'Ngày tạo', dataIndex: 'createdDate', width: 100},
            {
                xtype: 'actioncolumn',
                header: 'Option',
                sortable: false,
                menuDisabled: true,
                items: [this.deleteBtn()]
            }
        ];

        me.bbar = Ext.create('Ext.PagingToolbar', {
            store: me.store,
            displayInfo: true,
            displayMsg: 'Displaying topics {0} - {1} of {2}',
            emptyMsg: "No topics to display"
        });


        me.cellEditing = new Ext.grid.plugin.CellEditing({
            clicksToEdit: 1
        });

        me.plugins = [me.cellEditing];

        me.callParent(arguments);
    },
    deleteBtn: function () {
        return {
            icon: '/assets/img/icons/fam/delete.png',
            tooltip: 'Delete',
            handler: function (view, rowIndex, colIndex, item, e, record) {
                this.fireEvent('deleteRecord', this, view, rowIndex, colIndex, item, e, record);
            }
        }
    }
});