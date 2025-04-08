<template>
  <a-modal
    :visible="visible"
    :width="modal.width"
    :style="modal.style"
    :maskClosable="modal.maskClosable"
    :confirmLoading="confirmLoading"
    @ok="handleOk"
    @cancel="handleCancel"
    cancelText="关闭">

    <template slot="title">
      <div style="width: 100%;height:20px;padding-right:32px;">
        <div style="float: left;">{{ title }}</div>
        <div style="float: right;">
          <a-button
            icon="fullscreen"
            style="width:56px;height:100%;border:0"
            @click="handleClickToggleFullScreen"/>
        </div>
      </div>
    </template>

    <a-spin :spinning="confirmLoading">
      <a-form :form="form">

        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="用户id">
          <a-input placeholder="请输入用户id" v-decorator="['userId', validatorRules.userId]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="姓名">
          <a-input placeholder="请输入姓名" v-decorator="['name', validatorRules.name]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="电话">
          <a-input placeholder="请输入电话" v-decorator="['phoneNumber', validatorRules.phoneNumber]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="参观时间">
          <a-date-picker showTime format='YYYY-MM-DD HH:mm:ss' v-decorator="[ 'visitTime', validatorRules.visitTime]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="孕周">
          <a-input placeholder="请输入孕周" v-decorator="['gestWeek', validatorRules.gestWeek]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="住宅">
          <a-input placeholder="请输入住宅" v-decorator="['residence', validatorRules.residence]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="参观人数">
          <a-input placeholder="请输入参观人数" v-decorator="['number', validatorRules.number]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="产检医院">
          <a-input placeholder="请输入产检医院" v-decorator="['hospital', validatorRules.hospital]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="陪同人员关系">
          <a-input placeholder="请输入陪同人员关系" v-decorator="['relation', validatorRules.relation]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="是否带孩子">
          <a-input placeholder="请输入是否带孩子" v-decorator="['child', validatorRules.child]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="年龄段">
          <a-input placeholder="请输入年龄段" v-decorator="['age', validatorRules.age]" />
        </a-form-item>

      </a-form>
    </a-spin>
  </a-modal>
</template>

<script>
  import { getAction, httpAction } from '@/api/manage'
  import pick from 'lodash.pick'
  import moment from "moment"

  export default {
    name: "YzUserInfoModal",
    data () {
      return {
        modal: {
          width: 800,
          style: { top: '50px' },
          maskClosable: false,
          fullScreen: false
        },
        title:"操作",
        visible: false,
        model: {},
        labelCol: {
          xs: { span: 24 },
          sm: { span: 5 },
        },
        wrapperCol: {
          xs: { span: 24 },
          sm: { span: 16 },
        },

        confirmLoading: false,
        form: this.$form.createForm(this),
        validatorRules:{
        userId:{rules: [{ required: true, message: '请输入用户id!' }]},
        name:{rules: [{ required: true, message: '请输入姓名!' }]},
        phoneNumber:{rules: [{ required: true, message: '请输入电话!' }]},
        visitTime:{rules: [{ required: true, message: '请选择参观时间!' }]},
        gestWeek:{rules: [{ required: true, message: '请输入孕周!' }]},
        residence:{rules: [{ required: true, message: '请输入住宅!' }]},
        number:{rules: [{ required: true, message: '请输入参观人数!' }]},
        hospital:{rules: [{ required: true, message: '请输入产检医院!' }]},
        relation:{rules: [{ required: true, message: '请输入陪同人员关系!' }]},
        child:{rules: [{ required: true, message: '请输入是否带孩子!' }]},
        age:{rules: [{ required: true, message: '请输入年龄段!' }]},
        },
        url: {
          add: "/userInfo/add",
          edit: "/userInfo/edit",
          queryById: "/userInfo/queryById"
        },
      }
    },
    created () {
    },
    methods: {
      /** 切换全屏显示 */
      handleClickToggleFullScreen() {
        let mode = !this.modal.fullScreen
        if (mode) {
          this.modal.width = '100%'
          this.modal.style.top = '20px'
        } else {
          this.modal.width = '800px'
          this.modal.style.top = '50px'
        }
        this.modal.fullScreen = mode
      },
      add () {
        this.edit({});
      },
      edit (record) {
        this.form.resetFields();
        this.model = Object.assign({}, record);
        this.visible = true;
        if(record.id) {
           getAction(this.url.queryById + '?id=' + record.id,{}).then((res)=>{
            if(res.success){
              this.$nextTick(() => {
			    this.form.setFieldsValue(pick(res.result,'userId','name','phoneNumber','gestWeek','residence','number','hospital','relation','child','age'))
              });
            }
          }).finally(() => {
          })
        } else {
          this.$nextTick(() => {
			  this.form.setFieldsValue(pick(this.model,'userId','name','phoneNumber','gestWeek','residence','number','hospital','relation','child','age'))
			  //时间格式化
			  this.form.setFieldsValue({visitTime:this.model.visitTime?moment(this.model.visitTime):null})
          });
        }


      },
      close () {
        this.$emit('close');
        this.visible = false;
      },
      handleOk () {
        const that = this;
        // 触发表单验证
        this.form.validateFields((err, values) => {
          if (!err) {
            that.confirmLoading = true;
            let httpurl = '';
            let method = '';
            if(!this.model.id){
              httpurl+=this.url.add;
              method = 'post';
            }else{
              httpurl+=this.url.edit;
               method = 'put';
            }
            let formData = Object.assign(this.model, values);
            //时间格式化
            formData.visitTime = formData.visitTime?formData.visitTime.format('YYYY-MM-DD HH:mm:ss'):null;
            console.log(formData);
            httpAction(httpurl,formData,method).then((res)=>{
              if(res.success){
                that.$message.success(res.message);
                that.$emit('ok');
                that.close();
              }else{
                that.$message.warning(res.message);
              }
            }).finally(() => {
              that.confirmLoading = false;
            })

          }
        })
      },
      handleCancel () {
        this.close()
      },


    }
  }
</script>

<style lang="less" scoped>

</style>