using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace XJFaceWeb.Utility
{
    public static class ObjectExtensions
    {
        //[DebuggerStepThrough]
        public static T ToEnum<T>(this object instance) where T : struct, IComparable, IFormattable
        {
            return instance.ToEnum<T>(default(T));
        }
        //[DebuggerStepThrough]
        public static T ToEnum<T>(this object instance, T defaultValue) where T : struct, IComparable, IFormattable
        {
            T convertedValue = defaultValue;
            if (instance != null && !Enum.TryParse(instance.ToString(), true, out convertedValue))
            {
                convertedValue = defaultValue;
            }
            return convertedValue;
        }

    }
}