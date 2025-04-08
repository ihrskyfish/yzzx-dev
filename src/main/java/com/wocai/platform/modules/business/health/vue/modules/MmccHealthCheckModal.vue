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
          label="每天心情（0：不开心 1：普通 2：开心）">
          <a-input placeholder="请输入每天心情（0：不开心 1：普通 2：开心）" v-decorator="['mood', validatorRules.mood]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="体重kg">
          <a-input placeholder="请输入体重kg" v-decorator="['weight', validatorRules.weight]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="胸围cm">
          <a-input placeholder="请输入胸围cm" v-decorator="['bust', validatorRules.bust]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="腰围cm">
          <a-input placeholder="请输入腰围cm" v-decorator="['waist', validatorRules.waist]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="臀围cm">
          <a-input placeholder="请输入臀围cm" v-decorator="['hip', validatorRules.hip]" />
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
    name: "MmccHealthCheckModal",
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
        mood:{rules: [{ required: true, message: '请输入每天心情（0：不开心 1：普通 2：开心）!' }]},
        weight:{rules: [{ required: true, message: '请输入体重kg!' }]},
        bust:{rules: [{ required: true, message: '请输入胸围cm!' }]},
        waist:{rules: [{ required: true, message: '请输入腰围cm!' }]},
        hip:{rules: [{ required: true, message: '请输入臀围cm!' }]},
        },
        url: {
          add: "/health/check/add",
          edit: "/health/check/edit",
          queryById: "/health/check/queryById"
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
			    this.form.setFieldsValue(pick(res.result,'mood','weight','bust','waist','hip'))
              });
            }
          }).finally(() => {
          })
        } else {
          this.$nextTick(() => {
			  this.form.setFieldsValue(pick(this.model,'mood','weight','bust','waist','hip'))
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