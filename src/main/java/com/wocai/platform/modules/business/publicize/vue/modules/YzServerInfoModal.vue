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
          label="用户信息id">
          <a-input placeholder="请输入用户信息id" v-decorator="['userInfoId', validatorRules.userInfoId]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="婴儿床">
          <a-input placeholder="请输入婴儿床" v-decorator="['bed', validatorRules.bed]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="点心、饮品">
          <a-input placeholder="请输入点心、饮品" v-decorator="['food', validatorRules.food]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="月子餐品鉴">
          <a-input placeholder="请输入月子餐品鉴" v-decorator="['yzMealAppraise', validatorRules.yzMealAppraise]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="月子餐菜单">
          <a-input placeholder="请输入月子餐菜单" v-decorator="['yzMealMenu', validatorRules.yzMealMenu]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="陪护餐份数">
          <a-input placeholder="请输入陪护餐份数" v-decorator="['mealNumber', validatorRules.mealNumber]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="雨伞">
          <a-input placeholder="请输入雨伞" v-decorator="['umbrella', validatorRules.umbrella]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="保安泊车">
          <a-input placeholder="请输入保安泊车" v-decorator="['park', validatorRules.park]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="接送">
          <a-input placeholder="请输入接送" v-decorator="['pado', validatorRules.pado]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="儿童安全座椅">
          <a-input placeholder="请输入儿童安全座椅" v-decorator="['safeSeat', validatorRules.safeSeat]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="其他需求">
          <a-input placeholder="请输入其他需求" v-decorator="['other', validatorRules.other]" />
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
    name: "YzServerInfoModal",
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
        userInfoId:{rules: [{ required: true, message: '请输入用户信息id!' }]},
        bed:{rules: [{ required: true, message: '请输入婴儿床!' }]},
        food:{rules: [{ required: true, message: '请输入点心、饮品!' }]},
        yzMealAppraise:{rules: [{ required: true, message: '请输入月子餐品鉴!' }]},
        yzMealMenu:{rules: [{ required: true, message: '请输入月子餐菜单!' }]},
        mealNumber:{rules: [{ required: true, message: '请输入陪护餐份数!' }]},
        umbrella:{rules: [{ required: true, message: '请输入雨伞!' }]},
        park:{rules: [{ required: true, message: '请输入保安泊车!' }]},
        pado:{rules: [{ required: true, message: '请输入接送!' }]},
        safeSeat:{rules: [{ required: true, message: '请输入儿童安全座椅!' }]},
        other:{rules: [{ required: true, message: '请输入其他需求!' }]},
        },
        url: {
          add: "/serverInfo/add",
          edit: "/serverInfo/edit",
          queryById: "/serverInfo/queryById"
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
			    this.form.setFieldsValue(pick(res.result,'userId','userInfoId','bed','food','yzMealAppraise','yzMealMenu','mealNumber','umbrella','park','pado','safeSeat','other'))
              });
            }
          }).finally(() => {
          })
        } else {
          this.$nextTick(() => {
			  this.form.setFieldsValue(pick(this.model,'userId','userInfoId','bed','food','yzMealAppraise','yzMealMenu','mealNumber','umbrella','park','pado','safeSeat','other'))
			  //时间格式化
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