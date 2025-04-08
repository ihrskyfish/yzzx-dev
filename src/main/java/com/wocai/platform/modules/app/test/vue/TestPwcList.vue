<template>
  <a-card :bordered="false">

    <!-- 查询区域 -->
    <div class="table-page-search-wrapper">
      <a-form layout="inline">
        <a-row :gutter="24">
          <a-col :md="5" :sm="8">
            <a-form-item label="pwc_table">
			  <a-input placeholder="请输入pwc_table" v-model="queryParam.pwcTable" />
            </a-form-item>
          </a-col>
          <a-col :md="5" :sm="8">
            <a-form-item label="pwc_table_img">
			  <a-input placeholder="请输入pwc_table_img" v-model="queryParam.pwcTableImg" />
            </a-form-item>
          </a-col>
          <template v-if="toggleSearchStatus">
          <a-col :md="5" :sm="8">
            <a-form-item label="pwc_intr">
			  <a-input placeholder="请输入pwc_intr" v-model="queryParam.pwcIntr" />
            </a-form-item>
          </a-col>
          <a-col :md="5" :sm="8">
            <a-form-item label="pwc_text">
			  <a-input placeholder="请输入pwc_text" v-model="queryParam.pwcText" />
            </a-form-item>
          </a-col>
          <a-col :md="5" :sm="8">
            <a-form-item label="pwc_table_img_big">
			  <a-input placeholder="请输入pwc_table_img_big" v-model="queryParam.pwcTableImgBig" />
            </a-form-item>
          </a-col>
          </template>
          <a-col :md="6" :sm="8" >
            <span style="float: left;overflow: hidden;" class="table-page-search-submitButtons">
              <a-button type="primary" @click="searchQuery" icon="search">查询</a-button>
              <a-button type="primary" @click="searchReset" icon="reload" style="margin-left: 8px">重置</a-button>
              <a @click="handleToggleSearch" style="margin-left: 8px">
                {{ toggleSearchStatus ? '收起' : '展开' }}
                <a-icon :type="toggleSearchStatus ? 'up' : 'down'"/>
              </a>
            </span>
          </a-col>

        </a-row>
      </a-form>
    </div>

    <!-- 操作按钮区域 -->
    <div class="table-operator">
      <a-button @click="handleAdd" type="primary" icon="plus">新增</a-button>
      <a-button type="primary" icon="download" @click="handleExportXls('公益栏增删改查')">导出</a-button>
      <a-button type="primary" icon="import" @click="handleImport">导入</a-button>
      <a-dropdown v-if="selectedRowKeys.length > 0">
        <a-menu slot="overlay">
          <a-menu-item key="1" @click="batchDel"><a-icon type="delete"/>删除</a-menu-item>
        </a-menu>
        <a-button style="margin-left: 8px"> 批量操作 <a-icon type="down" /></a-button>
      </a-dropdown>
    </div>

    <!-- table区域-begin -->
    <div>
      <div class="ant-alert ant-alert-info" style="margin-bottom: 16px;">
        <i class="anticon anticon-info-circle ant-alert-icon"></i> 已选择 <a style="font-weight: 600">{{ selectedRowKeys.length }}</a>项
        <a style="margin-left: 24px" @click="onClearSelected">清空</a>
      </div>

      <a-table
        ref="table"
        size="middle"
        bordered
        rowKey="id"
        :columns="columns"
        :dataSource="dataSource"
        :pagination="ipagination"
        :loading="loading"
        :rowSelection="{selectedRowKeys: selectedRowKeys, onChange: onSelectChange}"
        @change="handleTableChange">

        <span slot="action" slot-scope="text, record">
          <a @click="handleEdit(record)">编辑</a>

          <a-divider type="vertical" />
          <a-dropdown>
            <a class="ant-dropdown-link">更多 <a-icon type="down" /></a>
            <a-menu slot="overlay">
              <a-menu-item>
                <a-popconfirm title="确定删除吗?" @confirm="() => handleDelete(record.id)">
                  <a>删除</a>
                </a-popconfirm>
              </a-menu-item>
            </a-menu>
          </a-dropdown>
        </span>

      </a-table>
    </div>
    <!-- table区域-end -->

    <!-- 表单区域 -->
    <testPwc-modal ref="modalForm" @ok="modalFormOk"></testPwc-modal>
    <!-- 导入 -->
    <j-import-modal ref="modalImport" :url="url.importExcelUrl" :downloadUrl="url.downloadUrl" @ok="importOk"></j-import-modal>
  </a-card>
</template>

<script>
  import TestPwcModal from './modules/TestPwcModal'
  import JImportModal from '@/components/jeecg/JImportModal'
  import { JeecgListMixin } from '@/mixins/JeecgListMixin'

  export default {
    name: "TestPwcList",
    mixins:[JeecgListMixin],
    components: {
      TestPwcModal,JImportModal
    },
    data () {
      return {
        description: '公益栏增删改查管理页面',
        // 表头
        columns: [
          {
            title: '#',
            dataIndex: '',
            key:'rowIndex',
            width:60,
            align:"center",
            customRender:function (t,r,index) {
              return parseInt(index)+1;
            }
           },
		   {
            title: 'pwc_table',
            align:"center",
            dataIndex: 'pwcTable'
           },
		   {
            title: 'pwc_table_img',
            align:"center",
            dataIndex: 'pwcTableImg'
           },
		   {
            title: 'pwc_intr',
            align:"center",
            dataIndex: 'pwcIntr'
           },
		   {
            title: 'pwc_text',
            align:"center",
            dataIndex: 'pwcText'
           },
		   {
            title: 'pwc_table_img_big',
            align:"center",
            dataIndex: 'pwcTableImgBig'
           },
          {
            title: '操作',
            dataIndex: 'action',
            align:"center",
            scopedSlots: { customRender: 'action' },
          }
        ],
		url: {
          list: "/test/list",
          delete: "/test/delete",
          deleteBatch: "/test/deleteBatch",
          exportXlsUrl: "test/exportXls",
          importExcelUrl: "test/importExcel",
          downloadUrl: ''
       },
    }
  },
  computed: {
    importExcelUrl: function(){
      return `${window._CONFIG['domianURL']}/${this.url.importExcelUrl}`;
    }
  },
    methods: {

    }
  }
</script>
<style scoped>
  @import '~@assets/less/common.less'
</style>