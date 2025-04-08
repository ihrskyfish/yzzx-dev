<template>
  <a-card :bordered="false">

    <!-- 查询区域 -->
    <div class="table-page-search-wrapper">
      <a-form layout="inline">
        <a-row :gutter="24">
          <a-col :md="5" :sm="8">
            <a-form-item label="用户id">
			  <a-input placeholder="请输入用户id" v-model="queryParam.userId" />
            </a-form-item>
          </a-col>
          <a-col :md="5" :sm="8">
            <a-form-item label="姓名">
			  <a-input placeholder="请输入姓名" v-model="queryParam.name" />
            </a-form-item>
          </a-col>
          <template v-if="toggleSearchStatus">
          <a-col :md="5" :sm="8">
            <a-form-item label="电话">
			  <a-input placeholder="请输入电话" v-model="queryParam.phoneNumber" />
            </a-form-item>
          </a-col>
          <a-col :md="5" :sm="8">
            <a-form-item label="参观时间">
			  <a-date-picker showTime format='YYYY-MM-DD HH:mm:ss' v-model="queryParam.visitTime" />
            </a-form-item>
          </a-col>
          <a-col :md="5" :sm="8">
            <a-form-item label="孕周">
			  <a-input placeholder="请输入孕周" v-model="queryParam.gestWeek" />
            </a-form-item>
          </a-col>
          <a-col :md="5" :sm="8">
            <a-form-item label="住宅">
			  <a-input placeholder="请输入住宅" v-model="queryParam.residence" />
            </a-form-item>
          </a-col>
          <a-col :md="5" :sm="8">
            <a-form-item label="参观人数">
			  <a-input placeholder="请输入参观人数" v-model="queryParam.number" />
            </a-form-item>
          </a-col>
          <a-col :md="5" :sm="8">
            <a-form-item label="产检医院">
			  <a-input placeholder="请输入产检医院" v-model="queryParam.hospital" />
            </a-form-item>
          </a-col>
          <a-col :md="5" :sm="8">
            <a-form-item label="陪同人员关系">
			  <a-input placeholder="请输入陪同人员关系" v-model="queryParam.relation" />
            </a-form-item>
          </a-col>
          <a-col :md="5" :sm="8">
            <a-form-item label="是否带孩子">
			  <a-input placeholder="请输入是否带孩子" v-model="queryParam.child" />
            </a-form-item>
          </a-col>
          <a-col :md="5" :sm="8">
            <a-form-item label="年龄段">
			  <a-input placeholder="请输入年龄段" v-model="queryParam.age" />
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
      <a-button type="primary" icon="download" @click="handleExportXls('用户信息')">导出</a-button>
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
    <yzUserInfo-modal ref="modalForm" @ok="modalFormOk"></yzUserInfo-modal>
    <!-- 导入 -->
    <j-import-modal ref="modalImport" :url="url.importExcelUrl" :downloadUrl="url.downloadUrl" @ok="importOk"></j-import-modal>
  </a-card>
</template>

<script>
  import YzUserInfoModal from './modules/YzUserInfoModal'
  import JImportModal from '@/components/jeecg/JImportModal'
  import { JeecgListMixin } from '@/mixins/JeecgListMixin'

  export default {
    name: "YzUserInfoList",
    mixins:[JeecgListMixin],
    components: {
      YzUserInfoModal,JImportModal
    },
    data () {
      return {
        description: '用户信息管理页面',
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
            title: '用户id',
            align:"center",
            dataIndex: 'userId'
           },
		   {
            title: '姓名',
            align:"center",
            dataIndex: 'name'
           },
		   {
            title: '电话',
            align:"center",
            dataIndex: 'phoneNumber'
           },
		   {
            title: '参观时间',
            align:"center",
            dataIndex: 'visitTime'
           },
		   {
            title: '孕周',
            align:"center",
            dataIndex: 'gestWeek'
           },
		   {
            title: '住宅',
            align:"center",
            dataIndex: 'residence'
           },
		   {
            title: '参观人数',
            align:"center",
            dataIndex: 'number'
           },
		   {
            title: '产检医院',
            align:"center",
            dataIndex: 'hospital'
           },
		   {
            title: '陪同人员关系',
            align:"center",
            dataIndex: 'relation'
           },
		   {
            title: '是否带孩子',
            align:"center",
            dataIndex: 'child'
           },
		   {
            title: '年龄段',
            align:"center",
            dataIndex: 'age'
           },
          {
            title: '操作',
            dataIndex: 'action',
            align:"center",
            scopedSlots: { customRender: 'action' },
          }
        ],
		url: {
          list: "/userInfo/list",
          delete: "/userInfo/delete",
          deleteBatch: "/userInfo/deleteBatch",
          exportXlsUrl: "userInfo/exportXls",
          importExcelUrl: "userInfo/importExcel",
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