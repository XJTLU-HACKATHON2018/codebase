using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using XJFaceWeb.Utility;

namespace XJFaceWeb.Models
{
    public class IdentityEntity
    {
        public long Id { get; set; }
        public bool IsActive { get; set; }
        public string Track_CreateTime { get; set; }
        public string Track_Creator { get; set; }
        public string Track_ModifyTime { get; set; }
        public string Track_Modifier { get; set; }
        public string Remark { get; set; }
        public string No { get; set; }
        public string Name { get; set; }
        public string Sex { get; set; }
        public string Nationality { get; set; }
        public string Address { get; set; }
        public string BirthDate { get; set; }
        public string IssueDate { get; set; }
        public string ExpiryDate { get; set; }
        public string Authority { get; set; }
        public byte[] Photo { get; set; }
        public string Company { get; set; }
        public string Title { get; set; }
        public string Email { get; set; }
        public string Tel { get; set; }

        public string EXT_1 { get; set; } //角色 
        public string EXT_2 { get; set; } //黑名单
        public string EXT_3 { get; set; } //欢迎词
        public string EXT_4 { get; set; } //空间id
        public string EXT_5 { get; set; } //user的Guid
        public string EXT_6 { get; set; } //卡编号
        public string Sex_Name
        {
            get
            {
                return string.IsNullOrEmpty(Sex) ? "未知" : Sex.ToEnum<IdentitySex>().ToString();
            }
        }
    }
    public enum IdentitySex
    {
        男 = 1,
        女 = 2,
    }
}