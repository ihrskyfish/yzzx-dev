<template>
  <a-card :bordered="false">

    <!-- 查询区域 -->
    <div class="table-page-search-wrapper">
      <a-form layout="inline">
        <a-row :gutter="24">
          <a-col :md="5" :sm="8">
            <a-form-item label="标题">
			  <a-input placeholder="请输入标题" v-model="queryParam.knwTable" />
            </a-form-item>
          </a-col>
          <a-col :md="5" :sm="8">
            <a-form-item label="简介">
			  <a-input placeholder="请输入简介" v-model="queryParam.intr" />
            </a-form-item>
          </a-col>
          <template v-if="toggleSearchStatus">
          <a-col :md="5" :sm="8">
            <a-form-item label="正文">
			  <a-input placeholder="请输入正文" v-model="queryParam.text" />
            </a-form-item>
          </a-col>
          <a-col :md="5" :sm="8">
            <a-form-item label="图片">
			  <a-input placeholder="请输入图片" v-model="queryParam.imgBig" />
            </a-form-item>
          </a-col>
          <a-col :md="5" :sm="8">
            <a-form-item label="分类id">
			  <a-input placeholder="请输入分类id" v-model="queryParam.categoryId" />
            </a-form-item>
          </a-col>
          <a-col :md="5" :sm="8">
            <a-form-item label="0表示未收藏1表示收藏">
			  <a-input placeholder="请输入0表示未收藏1表示收藏" v-model="queryParam.collectFlag" />
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
      <a-button type="primary" icon="download" @click="handleExportXls('护理知识')">导出</a-button>
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
    <yzNsKnowledge-modal ref="modalForm" @ok="modalFormOk"></yzNsKnowledge-modal>
    <!-- 导入 -->
    <j-import-modal ref="modalImport" :url="url.importExcelUrl" :downloadUrl="url.downloadUrl" @ok="importOk"></j-import-modal>
  </a-card>
</template>

<script>
  import YzNsKnowledgeModal from './modules/YzNsKnowledgeModal'
  import JImportModal from '@/components/jeecg/JImportModal'
  import { JeecgListMixin } from '@/mixins/JeecgListMixin'

  export default {
    name: "YzNsKnowledgeList",
    mixins:[JeecgListMixin],
    components: {
      YzNsKnowledgeModal,JImportModal
    },
    data () {
      return {
        description: '护理知识管理页面',
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
            title: '标题',
            align:"center",
            dataIndex: 'knwTable'
           },
		   {
            title: '简介',
            align:"center",
            dataIndex: 'intr'
           },
		   {
            title: '正文',
            align:"center",
            dataIndex: 'text'
           },
		   {
            title: '图片',
            align:"center",
            dataIndex: 'imgBig'
           },
		   {
            title: '分类id',
            align:"center",
            dataIndex: 'categoryId'
           },
		   {
            title: '0表示未收藏1表示收藏',
            align:"center",
            dataIndex: 'collectFlag'
           },
          {
            title: '操作',
            dataIndex: 'action',
            align:"center",
            scopedSlots: { customRender: 'action' },
          }
        ],
		url: {
          list: "/nurse/konwledge/list",
          delete: "/nurse/konwledge/delete",
          deleteBatch: "/nurse/konwledge/deleteBatch",
          exportXlsUrl: "nurse/konwledge/exportXls",
          importExcelUrl: "nurse/konwledge/importExcel",
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