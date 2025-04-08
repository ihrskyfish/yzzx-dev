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
            <a-form-item label="用户信息id">
			  <a-input placeholder="请输入用户信息id" v-model="queryParam.userInfoId" />
            </a-form-item>
          </a-col>
          <template v-if="toggleSearchStatus">
          <a-col :md="5" :sm="8">
            <a-form-item label="婴儿床">
			  <a-input placeholder="请输入婴儿床" v-model="queryParam.bed" />
            </a-form-item>
          </a-col>
          <a-col :md="5" :sm="8">
            <a-form-item label="点心、饮品">
			  <a-input placeholder="请输入点心、饮品" v-model="queryParam.food" />
            </a-form-item>
          </a-col>
          <a-col :md="5" :sm="8">
            <a-form-item label="月子餐品鉴">
			  <a-input placeholder="请输入月子餐品鉴" v-model="queryParam.yzMealAppraise" />
            </a-form-item>
          </a-col>
          <a-col :md="5" :sm="8">
            <a-form-item label="月子餐菜单">
			  <a-input placeholder="请输入月子餐菜单" v-model="queryParam.yzMealMenu" />
            </a-form-item>
          </a-col>
          <a-col :md="5" :sm="8">
            <a-form-item label="陪护餐份数">
			  <a-input placeholder="请输入陪护餐份数" v-model="queryParam.mealNumber" />
            </a-form-item>
          </a-col>
          <a-col :md="5" :sm="8">
            <a-form-item label="雨伞">
			  <a-input placeholder="请输入雨伞" v-model="queryParam.umbrella" />
            </a-form-item>
          </a-col>
          <a-col :md="5" :sm="8">
            <a-form-item label="保安泊车">
			  <a-input placeholder="请输入保安泊车" v-model="queryParam.park" />
            </a-form-item>
          </a-col>
          <a-col :md="5" :sm="8">
            <a-form-item label="接送">
			  <a-input placeholder="请输入接送" v-model="queryParam.pado" />
            </a-form-item>
          </a-col>
          <a-col :md="5" :sm="8">
            <a-form-item label="儿童安全座椅">
			  <a-input placeholder="请输入儿童安全座椅" v-model="queryParam.safeSeat" />
            </a-form-item>
          </a-col>
          <a-col :md="5" :sm="8">
            <a-form-item label="其他需求">
			  <a-input placeholder="请输入其他需求" v-model="queryParam.other" />
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
      <a-button type="primary" icon="download" @click="handleExportXls('房间预定用户信息')">导出</a-button>
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
    <yzServerInfo-modal ref="modalForm" @ok="modalFormOk"></yzServerInfo-modal>
    <!-- 导入 -->
    <j-import-modal ref="modalImport" :url="url.importExcelUrl" :downloadUrl="url.downloadUrl" @ok="importOk"></j-import-modal>
  </a-card>
</template>

<script>
  import YzServerInfoModal from './modules/YzServerInfoModal'
  import JImportModal from '@/components/jeecg/JImportModal'
  import { JeecgListMixin } from '@/mixins/JeecgListMixin'

  export default {
    name: "YzServerInfoList",
    mixins:[JeecgListMixin],
    components: {
      YzServerInfoModal,JImportModal
    },
    data () {
      return {
        description: '房间预定用户信息管理页面',
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
            title: '用户信息id',
            align:"center",
            dataIndex: 'userInfoId'
           },
		   {
            title: '婴儿床',
            align:"center",
            dataIndex: 'bed'
           },
		   {
            title: '点心、饮品',
            align:"center",
            dataIndex: 'food'
           },
		   {
            title: '月子餐品鉴',
            align:"center",
            dataIndex: 'yzMealAppraise'
           },
		   {
            title: '月子餐菜单',
            align:"center",
            dataIndex: 'yzMealMenu'
           },
		   {
            title: '陪护餐份数',
            align:"center",
            dataIndex: 'mealNumber'
           },
		   {
            title: '雨伞',
            align:"center",
            dataIndex: 'umbrella'
           },
		   {
            title: '保安泊车',
            align:"center",
            dataIndex: 'park'
           },
		   {
            title: '接送',
            align:"center",
            dataIndex: 'pado'
           },
		   {
            title: '儿童安全座椅',
            align:"center",
            dataIndex: 'safeSeat'
           },
		   {
            title: '其他需求',
            align:"center",
            dataIndex: 'other'
           },
          {
            title: '操作',
            dataIndex: 'action',
            align:"center",
            scopedSlots: { customRender: 'action' },
          }
        ],
		url: {
          list: "/serverInfo/list",
          delete: "/serverInfo/delete",
          deleteBatch: "/serverInfo/deleteBatch",
          exportXlsUrl: "serverInfo/exportXls",
          importExcelUrl: "serverInfo/importExcel",
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