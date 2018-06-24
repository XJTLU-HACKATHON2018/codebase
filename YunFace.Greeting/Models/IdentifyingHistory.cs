using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using YunFace.Client.Models;

namespace YunFace.Greeting.Models
{
    public class IdentifyingHistory
    {
        public IdentityInfo Identity { get; private set; }
        public DateTime LogTime { get; set; }

        public IdentifyingHistory(IdentityInfo identity)
        {
            Identity = identity;
            LogTime = DateTime.Now;
        }
    }
}
