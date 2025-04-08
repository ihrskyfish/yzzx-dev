<template>
  <a-card :bordered="false">

    <!-- 查询区域 -->
    <div class="table-page-search-wrapper">
      <a-form layout="inline">
        <a-row :gutter="24">
          <a-col :md="5" :sm="8">
            <a-form-item label="1开心2普通3不开心">
			  <j-dict-select-tag v-model="queryParam.mood" placeholder="请选择1开心2普通3不开心" dictCode=""/>
            </a-form-item>
          </a-col>
          <a-col :md="5" :sm="8">
            <a-form-item label="打卡天数">
			  <a-input placeholder="请输入打卡天数" v-model="queryParam.days" />
            </a-form-item>
          </a-col>
          <template v-if="toggleSearchStatus">
          <a-col :md="5" :sm="8">
            <a-form-item label="体重">
			  <a-input placeholder="请输入体重" v-model="queryParam.weight" />
            </a-form-item>
          </a-col>
          <a-col :md="5" :sm="8">
            <a-form-item label="胸围">
			  <a-input placeholder="请输入胸围" v-model="queryParam.bust" />
            </a-form-item>
          </a-col>
          <a-col :md="5" :sm="8">
            <a-form-item label="腰围">
			  <a-input placeholder="请输入腰围" v-model="queryParam.waist" />
            </a-form-item>
          </a-col>
          <a-col :md="5" :sm="8">
            <a-form-item label="臂围">
			  <a-input placeholder="请输入臂围" v-model="queryParam.arm" />
            </a-form-item>
          </a-col>
          <a-col :md="5" :sm="8">
            <a-form-item label="b超图">
			  <a-input placeholder="请输入b超图" v-model="queryParam.bImgs" />
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
      <a-button type="primary" icon="download" @click="handleExportXls('孕期健康')">导出</a-button>
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
    <yzPregHealth-modal ref="modalForm" @ok="modalFormOk"></yzPregHealth-modal>
    <!-- 导入 -->
    <j-import-modal ref="modalImport" :url="url.importExcelUrl" :downloadUrl="url.downloadUrl" @ok="importOk"></j-import-modal>
  </a-card>
</template>

<script>
  import YzPregHealthModal from './modules/YzPregHealthModal'
  import JImportModal from '@/components/jeecg/JImportModal'
  import { JeecgListMixin } from '@/mixins/JeecgListMixin'

  export default {
    name: "YzPregHealthList",
    mixins:[JeecgListMixin],
    components: {
      YzPregHealthModal,JImportModal
    },
    data () {
      return {
        description: '孕期健康管理页面',
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
            title: '1开心2普通3不开心',
            align:"center",
            dataIndex: 'mood'
           },
		   {
            title: '打卡天数',
            align:"center",
            dataIndex: 'days'
           },
		   {
            title: '体重',
            align:"center",
            dataIndex: 'weight'
           },
		   {
            title: '胸围',
            align:"center",
            dataIndex: 'bust'
           },
		   {
            title: '腰围',
            align:"center",
            dataIndex: 'waist'
           },
		   {
            title: '臂围',
            align:"center",
            dataIndex: 'arm'
           },
		   {
            title: 'b超图',
            align:"center",
            dataIndex: 'bImgs'
           },
          {
            title: '操作',
            dataIndex: 'action',
            align:"center",
            scopedSlots: { customRender: 'action' },
          }
        ],
		url: {
          list: "/health/list",
          delete: "/health/delete",
          deleteBatch: "/health/deleteBatch",
          exportXlsUrl: "health/exportXls",
          importExcelUrl: "health/importExcel",
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